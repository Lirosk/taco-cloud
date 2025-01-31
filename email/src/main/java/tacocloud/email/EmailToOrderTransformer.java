package tacocloud.email;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.integration.mail.transformer.AbstractMailMessageTransformer;
import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import lombok.RequiredArgsConstructor;
import tacocloud.data.IngredientRepository;
import tacocloud.domain.Ingredient;
import tacocloud.domain.Order;
import tacocloud.domain.Taco;

@Component
@RequiredArgsConstructor
public class EmailToOrderTransformer extends AbstractMailMessageTransformer<Order> {
    private static final String SUBJECT_KEYWORD = "TACO ORDER";

    private final IngredientRepository ingredientRepository;

    @Override
    protected AbstractIntegrationMessageBuilder<Order> doTransform(Message mailMessage) {
        Order order = processPayload(mailMessage);
        return MessageBuilder.withPayload(order);
    }

    private Order processPayload(Message mailMessage) {
        try {
            String subject = mailMessage.getSubject();
            if (subject.toUpperCase().contains(SUBJECT_KEYWORD)) {
                String email = ((InternetAddress) mailMessage.getFrom()[0]).getAddress();
                String content = mailMessage.getContent().toString();
                return parseEmailToOrder(email, content);
            }
        } catch (MessagingException | IOException _) {
        }
        return null;
    }

    private Order parseEmailToOrder(String email, String content) {
        Order order = new Order();
        order.setEmail(email);
        String[] lines = content.split("\\r?\\n");
        for (String line : lines) {
            if (!line.trim().isEmpty() && line.contains(":")) {
                String[] lineSplit = line.split(":");
                String tacoName = lineSplit[0].trim();
                String ingredients = lineSplit[1].trim();
                String[] ingredientsSplit = ingredients.split(",");
                List<String> ingredientIds = new ArrayList<>();
                for (String ingredientName : ingredientsSplit) {
                    String id = lookupIngredientCode(ingredientName.trim());
                    if (id != null) {
                        ingredientIds.add(id);
                    }
                }

                List<Ingredient> tacoIngredients = new ArrayList<>();
                for (String ingredientId : ingredientIds) {
                    ingredientRepository.findById(ingredientId).ifPresent(tacoIngredients::add);
                }

                Taco taco = new Taco();
                taco.setName(tacoName);
                taco.getIngredients().addAll(tacoIngredients);
                order.getTacos().add(taco);
            }
        }
        return order;
    }

    private String lookupIngredientCode(String ingredientName) {
        for (Ingredient ingredient : ingredientRepository.findAll()) {
            String ucIngredientName = ingredientName.toUpperCase();
            if (LevenshteinDistance.getDefaultInstance().apply(ucIngredientName, ingredient.getName()) < 3 || ucIngredientName.contains(ingredient.getName()) || ingredient.getName().contains(ucIngredientName)) {
                return ingredient.getId();
            }
        }
        return null;
    }

}
