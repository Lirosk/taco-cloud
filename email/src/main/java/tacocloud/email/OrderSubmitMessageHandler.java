package tacocloud.email;

import org.springframework.integration.core.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import tacocloud.domain.Order;

@Component
@RequiredArgsConstructor
public class OrderSubmitMessageHandler implements GenericHandler<Order> {
    private final RestTemplate restTemplate;
    private final ApiProperties apiProperties;

    @Override
    public Object handle(Order payload, MessageHeaders headers) {
        restTemplate.postForObject(apiProperties.getUrl(), payload, String.class);
        return null;
    }
}
