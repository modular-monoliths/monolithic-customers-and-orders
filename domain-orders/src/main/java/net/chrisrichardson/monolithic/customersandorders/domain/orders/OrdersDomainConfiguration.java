package net.chrisrichardson.monolithic.customersandorders.domain.orders;

import net.chrisrichardson.monolithic.customersandorders.domain.customers.CustomerRepository;
import net.chrisrichardson.monolithic.customersandorders.domain.customers.CustomersDomainConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@EntityScan
@Import(CustomersDomainConfiguration.class)
public class OrdersDomainConfiguration {

  @Bean
  public OrderService orderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
    return new OrderService(orderRepository, customerRepository);
  }
}
