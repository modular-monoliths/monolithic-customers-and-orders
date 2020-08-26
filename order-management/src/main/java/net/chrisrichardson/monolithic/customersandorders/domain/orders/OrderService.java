package net.chrisrichardson.monolithic.customersandorders.domain.orders;

import org.springframework.transaction.annotation.Transactional;

public interface OrderService {
  @Transactional
  Order createOrder(OrderDetails orderDetails);

  @Transactional
  Order cancelOrder(Long orderId);
}
