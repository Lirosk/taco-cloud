package tacocloud.messagingjms.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.RequiredArgsConstructor;
import tacocloud.domain.Order;

@Service
@RequiredArgsConstructor
public class JmsOrderMessagingService implements OrderMessagingService {
    private final JmsTemplate jms;

    @Override
    public void sendOrder(Order order) {
        jms.convertAndSend("tacocloud.order.queue", order, JmsOrderMessagingService::addOrderSource);
    }

    private static Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }
}
