package tacocloud.messaging;

import tacocloud.domain.Order;

public interface OrderMessagingService {
    void sendOrder(Order order);
}
