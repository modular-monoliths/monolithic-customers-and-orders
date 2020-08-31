package net.chrisrichardson.monolithic.customersandorders.domain.orders.api;

public interface OrderService {
  OrderDto createOrder(OrderDetails orderDetails);

  OrderDto cancelOrder(Long orderId);
}
