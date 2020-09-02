package net.chrisrichardson.monolithic.customersandorders.application;

import io.eventuate.examples.tram.ordersandcustomers.commonswagger.CommonSwaggerConfiguration;
import net.chrisrichardson.monolithic.customersandorders.domain.customers.CustomersDomainConfiguration;
import net.chrisrichardson.monolithic.customersandorders.domain.orders.OrdersDomainConfiguration;
import net.chrisrichardson.monolithic.customersandorders.web.customers.CustomersWebConfiguration;
import net.chrisrichardson.monolithic.customersandorders.web.orderhistory.OrderHistoryWebConfiguration;
import net.chrisrichardson.monolithic.customersandorders.web.orders.OrdersWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CustomersDomainConfiguration.class)
public class CustomersAndOrdersMain {
  public static void main(String[] args) {
    SpringApplication.run(CustomersAndOrdersMain.class, args);
  }
}
