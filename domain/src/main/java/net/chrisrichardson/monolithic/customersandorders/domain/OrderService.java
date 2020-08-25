package net.chrisrichardson.monolithic.customersandorders.domain;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Transactional;

public class OrderService {

  private final OrderRepository orderRepository;
  private final CustomerRepository customerRepository;


  public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
    this.orderRepository = orderRepository;
    this.customerRepository = customerRepository;
  }

  @Transactional
  public Order createOrder(OrderDetails orderDetails) {
    Customer customer =
            customerRepository.findById(orderDetails.getCustomerId())
                    .orElseThrow(() -> new ObjectRetrievalFailureException(Customer.class, "Customer not found " + orderDetails.getCustomerId()) {
                    });
    customer.reserveCredit(orderDetails.getOrderTotal());
    Order order = Order.createOrder(orderDetails.getOrderTotal(), customer);
    orderRepository.save(order);
    return order;
  }



  @Transactional
  public Order cancelOrder(Long orderId) {
    Order order = orderRepository
            .findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException(String.format("order with id %s not found", orderId)));
    order.cancel();
    return order;
  }
}
