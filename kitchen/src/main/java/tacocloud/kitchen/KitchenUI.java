package tacocloud.kitchen;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import tacocloud.domain.Order;

@Component
@Slf4j
public class KitchenUI {
    public void displayOrder(Order order) {
        log.info("ORDER RECEIVED: {}", order);
    }
}
