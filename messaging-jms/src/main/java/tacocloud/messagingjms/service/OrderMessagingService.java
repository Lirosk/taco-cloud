package tacocloud.messagingjms.service;


import tacocloud.domain.Order;

public interface OrderMessagingService {
    void sendOrder(Order order);
}
