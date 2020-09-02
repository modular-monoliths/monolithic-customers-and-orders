package net.chrisrichardson.monolithic.customersandorders.web.orderhistory;

import net.chrisrichardson.monolithic.customersandorders.domain.customers.api.CustomerDto;
import net.chrisrichardson.monolithic.customersandorders.domain.customers.api.CustomerService;
import net.chrisrichardson.monolithic.customersandorders.domain.orders.api.OrderDto;
import net.chrisrichardson.monolithic.customersandorders.domain.orders.api.OrderService;
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

  private final CustomerService customerService;
  private final OrderService orderService;

  @Autowired
  public CustomerOrderHistoryController(CustomerService customerService, OrderService orderService) {
    this.customerService = customerService;
    this.orderService = orderService;
  }


  @RequestMapping(value="/customers/{customerId}", method= RequestMethod.GET)
  public ResponseEntity<CustomerView> getCustomer(@PathVariable Long customerId) {
    return customerService
            .findById(customerId)
            .map(customer -> {
              List<OrderDto> orders = orderService.findByCustomerId(customer.getId());
              return new ResponseEntity<>(makeCustomerView(customer, orders), HttpStatus.OK);
            })
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  private CustomerView makeCustomerView(CustomerDto customer, List<OrderDto> orders) {
    return new CustomerView(customer.getName(), customer.getCreditLimit(),
            orders.stream().map(CustomerOrderHistoryController::makeOrderView).collect(Collectors.toList()));
  }

  private static OrderView makeOrderView(OrderDto order) {
    return new OrderView(order.getId(), order.getState(), order.getOrderTotal());
  }

}
