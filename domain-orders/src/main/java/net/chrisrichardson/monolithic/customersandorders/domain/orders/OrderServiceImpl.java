package net.chrisrichardson.monolithic.customersandorders.domain.orders;

import net.chrisrichardson.monolithic.customersandorders.domain.common.Money;
import net.chrisrichardson.monolithic.customersandorders.domain.customers.api.CustomerService;
import net.chrisrichardson.monolithic.customersandorders.domain.orders.api.OrderDetails;
import net.chrisrichardson.monolithic.customersandorders.domain.orders.api.OrderDto;
import net.chrisrichardson.monolithic.customersandorders.domain.orders.api.OrderService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final CustomerService customerService;


  public OrderServiceImpl(OrderRepository orderRepository, CustomerService customerService) {
    this.orderRepository = orderRepository;
    this.customerService = customerService;
  }

  @Override
  @Transactional
  public OrderDto createOrder(OrderDetails orderDetails) {
    Long customerId = orderDetails.getCustomerId();
    Money orderTotal = orderDetails.getOrderTotal();
    customerService.reserveCredit(customerId, orderTotal);
    Order order = Order.createOrder(orderTotal, customerId);
    orderRepository.save(order);
    return makeOrderDto(order);
  }

  private OrderDto makeOrderDto(Order order) {
    return new OrderDto(order.getId(), order.getState().name());
  }


  @Override
  @Transactional
  public OrderDto cancelOrder(Long orderId) {
    Order order = orderRepository
            .findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException(String.format("order with id %s not found", orderId)));
    order.cancel();
    customerService.unreserveCredit(order.getCustomerId(), order.getOrderTotal());
    return makeOrderDto(order);
  }

  @Override
  public Optional<OrderDto> findById(long orderId) {
    return orderRepository.findById(orderId).map(this::makeOrderDto);
  }

  @Override
  public List<OrderDto> findByCustomerId(long customerId) {
    return orderRepository.findByCustomerId(customerId).stream().map(this::makeOrderDto).collect(Collectors.toList());
  }
}
