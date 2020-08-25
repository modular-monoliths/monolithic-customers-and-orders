package net.chrisrichardson.monolithic.customersandorders.domain.orders.api;

import net.chrisrichardson.monolithic.customersandorders.domain.orders.Order;
import net.chrisrichardson.monolithic.customersandorders.domain.orders.OrderDetails;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {
  @Transactional
  Order createOrder(OrderDetails orderDetails);

  @Transactional
  Order cancelOrder(Long orderId);
}
