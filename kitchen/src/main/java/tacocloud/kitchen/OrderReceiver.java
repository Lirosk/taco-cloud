package tacocloud.kitchen;

import tacocloud.domain.Order;

public interface OrderReceiver {
    Order receiveOrder();
}
