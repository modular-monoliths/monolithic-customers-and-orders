package net.chrisrichardson.monolithic.customersandorders.domain.customers;

import net.chrisrichardson.monolithic.customersandorders.domain.customers.api.CustomerService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@EntityScan
public class CustomersDomainConfiguration {

  @Bean
  public CustomerService customerService(CustomerRepository customerRepository) {
    return new CustomerServiceImpl(customerRepository);
  }

}
