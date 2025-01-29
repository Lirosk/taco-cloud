package tacocloud.kitchen;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tacocloud.domain.Order;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderListener {
    private final KitchenUI kitchenUI;

    @KafkaListener(topics = "tacocloud.orders.topic", groupId = "kitchen")
    public void handle(Order order, ConsumerRecord<String, Order> record) {
        log.info("Received from partition {} with timestamp {}, order: {}", record.partition(), record.timestamp(), order);
        kitchenUI.displayOrder(order);
    }
}
