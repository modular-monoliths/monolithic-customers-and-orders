package net.chrisrichardson.monolithic.customersandorders.web.orderhistory;

import net.chrisrichardson.monolithic.customersandorders.domain.customers.api.CustomerDetails;
import net.chrisrichardson.monolithic.customersandorders.domain.customers.api.CustomerDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerOrderHistoryController {

  private final CustomerDetailsRepository customerRepository;
  private final OrderViewRepository orderRepository;

  @Autowired
  public CustomerOrderHistoryController(CustomerDetailsRepository customerRepository, OrderViewRepository orderRepository) {
    this.customerRepository = customerRepository;
    this.orderRepository = orderRepository;
  }

  @RequestMapping(value="/customers/{customerId}", method= RequestMethod.GET)
  public ResponseEntity<CustomerView> getCustomer(@PathVariable Long customerId) {
    return customerRepository
            .findById(customerId)
            .map(customer -> {
              List<OrderView> orders = orderRepository.findByCustomerId(customerId);
              return new ResponseEntity<>(makeCustomerView(customer, orders), HttpStatus.OK);
            })
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  private CustomerView makeCustomerView(CustomerDetails customer, List<OrderView> orders) {
    return new CustomerView(customer.getName(), customer.getCreditLimit(), orders);
  }

}
