package net.chrisrichardson.monolithic.customersandorders.domain.orders;

import net.chrisrichardson.monolithic.customersandorders.domain.customers.api.AvailableCreditManagement;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@EntityScan
public class OrdersDomainConfiguration {

  @Bean
  public OrderServiceImpl orderService(OrderRepository orderRepository, AvailableCreditManagement customerService) {
    return new OrderServiceImpl(orderRepository, customerService);
  }
}
