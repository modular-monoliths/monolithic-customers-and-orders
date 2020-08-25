package net.chrisrichardson.monolithic.customersandorders.domain.orders;

import net.chrisrichardson.monolithic.customersandorders.domain.common.Money;
import net.chrisrichardson.monolithic.customersandorders.domain.customers.api.CustomerService;
import net.chrisrichardson.monolithic.customersandorders.domain.orders.api.OrderService;
import org.springframework.transaction.annotation.Transactional;

public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final CustomerService customerService;


  public OrderServiceImpl(OrderRepository orderRepository, CustomerService customerService) {
    this.orderRepository = orderRepository;
    this.customerService = customerService;
  }

  @Override
  @Transactional
  public Order createOrder(OrderDetails orderDetails) {
    Long customerId = orderDetails.getCustomerId();
    Money orderTotal = orderDetails.getOrderTotal();
    customerService.reserveCredit(customerId, orderTotal);
    Order order = Order.createOrder(orderTotal, customerId);
    orderRepository.save(order);
    return order;
  }



  @Override
  @Transactional
  public Order cancelOrder(Long orderId) {
    Order order = orderRepository
            .findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException(String.format("order with id %s not found", orderId)));
    order.cancel();
    customerService.unreserveCredit(order.getCustomerId(), order.getOrderTotal());
    return order;
  }
}
