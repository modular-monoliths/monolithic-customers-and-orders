package net.chrisrichardson.monolithic.customersandorders.domain.orders.api;

import java.util.List;
import java.util.Optional;

public interface OrderService {
  OrderDto createOrder(OrderDetails orderDetails);

  OrderDto cancelOrder(Long orderId);

  Optional<OrderDto> findById(long orderId);

  List<OrderDto> findByCustomerId(long id);
}
