package com.example.tacocloud;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.tacocloud.models.Ingredient;
import com.example.tacocloud.models.Order;
import com.example.tacocloud.models.Taco;
import com.example.tacocloud.repositories.IngredientRepository;
import com.example.tacocloud.repositories.OrderRepository;
import com.example.tacocloud.repositories.TacoRepository;
import com.example.tacocloud.repositories.UserRepository;
import com.example.tacocloud.users.CustomUserDetails;

@Profile("prod")
@Configuration
public class DevelopmentConfig {
    @Bean
    public CommandLineRunner dataLoader(IngredientRepository ingredientRepository, UserRepository userRepository, TacoRepository tacoRepository, OrderRepository orderRepository, PasswordEncoder encoder) {
        return _ -> {
            List<Ingredient> ingredients = Arrays.asList(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP), new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP), new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN), new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN), new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES), new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES), new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE), new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE), new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE), new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));

            ingredientRepository.saveAll(ingredients);

            List<Taco> tacos = Arrays.asList(new Taco(), new Taco());

            tacos.get(0).setCreatedAt(new Date());
            tacos.get(0).setName("приве тсексуалка");
            tacos.get(0).setIngredients(Arrays.asList(ingredients.get(0), ingredients.get(2), ingredients.get(4), ingredients.get(6), ingredients.get(8)));

            tacos.get(1).setCreatedAt(new Date());
            tacos.get(1).setName("abiboba");
            tacos.get(1).setIngredients(Arrays.asList(ingredients.get(1), ingredients.get(3), ingredients.get(5), ingredients.get(7), ingredients.get(0)));

            tacoRepository.saveAll(tacos);

            CustomUserDetails user = new CustomUserDetails("buzz", encoder.encode("qwe"), "Craig Walls", "123 North Street", "Cross Roads", "TX", "76227", "123-123-1234");

            userRepository.save(user);

            List<Order> orders = Arrays.asList(
                    new Order(),
                    new Order()
            );

            for (Order order : orders) {
                order.setUser(user);
                order.setDeliveryZip("123");
                order.setDeliveryZip(user.getZip());
                order.setDeliveryStreet(user.getStreet());
                order.setDeliveryState(user.getState());
                order.setDeliveryName(user.getUsername());
                order.setDeliveryCity(user.getCity());
                order.setCcNumber("123");
                order.setCcExpiration("06/28");
                order.setCcCVV("123");
            }

            orders.get(0).getTacos().add(tacos.get(0));
            orders.get(0).setPlacedAt(new Date());

            orders.get(1).getTacos().add(tacos.get(1));
            orders.get(1).setPlacedAt(new Date(System.currentTimeMillis() + Duration.ofDays(1).toMillis()));

            orderRepository.saveAll(orders);
        };
    }
}
