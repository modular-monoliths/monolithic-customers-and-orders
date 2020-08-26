package net.chrisrichardson.monolithic.customersandorders.domain.customers;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@EntityScan
public class CustomersDomainConfiguration {

  @Bean
  public CustomerServiceImpl customerService(CustomerRepository customerRepository) {
    return new CustomerServiceImpl(customerRepository);
  }

}
