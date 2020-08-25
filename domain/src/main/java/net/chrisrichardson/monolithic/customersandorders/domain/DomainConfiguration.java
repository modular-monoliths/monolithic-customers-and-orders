package net.chrisrichardson.monolithic.customersandorders.domain;

import net.chrisrichardson.monolithic.customersandorders.domain.customers.CustomersDomainConfiguration;
import net.chrisrichardson.monolithic.customersandorders.domain.orders.OrdersDomainConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@EntityScan
@Import({CustomersDomainConfiguration.class, OrdersDomainConfiguration.class})
public class DomainConfiguration {

}
