package net.chrisrichardson.monolithic.customersandorders.domain.orders;

import net.chrisrichardson.monolithic.customersandorders.domain.customers.api.CustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrdersDomainConfiguration {

  @Bean
  public OrderServiceImpl orderService(OrderRepository orderRepository, CustomerService customerService) {
    return new OrderServiceImpl(orderRepository, customerService);
  }
}
