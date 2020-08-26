package net.chrisrichardson.monolithic.customersandorders.domain.orders;

import net.chrisrichardson.monolithic.customersandorders.domain.money.Money;
import net.chrisrichardson.monolithic.customersandorders.domain.customers.api.AvailableCreditManagement;
import net.chrisrichardson.monolithic.customersandorders.web.orderhistory.OrderView;
import net.chrisrichardson.monolithic.customersandorders.web.orderhistory.OrderViewRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService, OrderViewRepository {

  private final OrderRepository orderRepository;
  private final AvailableCreditManagement customerService;


  public OrderServiceImpl(OrderRepository orderRepository, AvailableCreditManagement customerService) {
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

  @Override
  public List<OrderView> findByCustomerId(long customerId) {
    return orderRepository.findByCustomerId(customerId).stream().map(o ->
            new OrderView(o.getId(), o.getState().name(), o.getOrderTotal())).collect(Collectors.toList());
  }
}
