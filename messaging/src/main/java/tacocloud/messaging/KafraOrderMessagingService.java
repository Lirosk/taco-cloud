package tacocloud.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tacocloud.domain.Order;

@Service
@RequiredArgsConstructor
public class KafraOrderMessagingService implements OrderMessagingService {
    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Override
    public void sendOrder(Order order) {
        kafkaTemplate.send("tacocloud.orders.topic", order);
    }
}
