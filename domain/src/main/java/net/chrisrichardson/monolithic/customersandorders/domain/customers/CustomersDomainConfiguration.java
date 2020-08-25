package net.chrisrichardson.monolithic.customersandorders.domain.customers;

import net.chrisrichardson.monolithic.customersandorders.domain.customers.api.CustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomersDomainConfiguration {

  @Bean
  public CustomerService customerService(CustomerRepository customerRepository) {
    return new CustomerServiceImpl(customerRepository);
  }

}
