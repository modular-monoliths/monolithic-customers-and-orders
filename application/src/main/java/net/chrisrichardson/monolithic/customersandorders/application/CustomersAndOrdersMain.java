package net.chrisrichardson.monolithic.customersandorders.application;

import io.eventuate.examples.tram.ordersandcustomers.commonswagger.CommonSwaggerConfiguration;
import net.chrisrichardson.monolithic.customersandorders.web.customers.CustomerWebConfiguration;
import net.chrisrichardson.monolithic.customersandorders.web.orders.OrderHistoryWebConfiguration;
import net.chrisrichardson.monolithic.customersandorders.web.orders.OrdersWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({CustomerWebConfiguration.class, OrdersWebConfiguration.class, OrderHistoryWebConfiguration.class,
        CommonSwaggerConfiguration.class})
public class CustomersAndOrdersMain {
  public static void main(String[] args) {
    SpringApplication.run(CustomersAndOrdersMain.class, args);
  }
}
