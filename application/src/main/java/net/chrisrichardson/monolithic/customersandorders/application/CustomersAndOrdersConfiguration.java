package net.chrisrichardson.monolithic.customersandorders.application;

import io.eventuate.examples.tram.ordersandcustomers.commonswagger.CommonSwaggerConfiguration;
import net.chrisrichardson.monolithic.customersandorders.web.customers.CustomersWebConfiguration;
import net.chrisrichardson.monolithic.customersandorders.web.orderhistory.OrderHistoryWebConfiguration;
import net.chrisrichardson.monolithic.customersandorders.web.orders.OrdersWebConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CustomersWebConfiguration.class, OrdersWebConfiguration.class, OrderHistoryWebConfiguration.class, CommonSwaggerConfiguration.class})
public class CustomersAndOrdersConfiguration {
}
