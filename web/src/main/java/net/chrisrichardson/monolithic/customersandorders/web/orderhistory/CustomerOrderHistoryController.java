package net.chrisrichardson.monolithic.customersandorders.web.orderhistory;

import net.chrisrichardson.monolithic.customersandorders.domain.customers.Customer;
import net.chrisrichardson.monolithic.customersandorders.domain.customers.CustomerRepository;
import net.chrisrichardson.monolithic.customersandorders.domain.orders.Order;
import net.chrisrichardson.monolithic.customersandorders.domain.orders.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerOrderHistoryController {

  private final CustomerRepository customerRepository;
  private final OrderRepository orderRepository;

  @Autowired
  public CustomerOrderHistoryController(CustomerRepository customerRepository, OrderRepository orderRepository) {
    this.customerRepository = customerRepository;
    this.orderRepository = orderRepository;
  }

  @RequestMapping(value="/customers/{customerId}", method= RequestMethod.GET)
  public ResponseEntity<CustomerView> getCustomer(@PathVariable Long customerId) {
    return customerRepository
            .findById(customerId)
            .map(customer -> {
              List<Order> orders = orderRepository.findByCustomerId(customer.getId());
              return new ResponseEntity<CustomerView>(makeCustomerView(customer, orders), HttpStatus.OK);
            })
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  private CustomerView makeCustomerView(Customer customer, List<Order> orders) {
    return new CustomerView(customer.getName(), customer.getCreditLimit(),
            orders.stream().map(CustomerOrderHistoryController::makeOrderView).collect(Collectors.toList()));
  }

  private static OrderView makeOrderView(Order order) {
    return new OrderView(order.getId(), order.getState(), order.getOrderTotal());
  }

}
