package net.chrisrichardson.monolithic.customersandorders.web.orders;

import net.chrisrichardson.monolithic.customersandorders.domain.orders.OrdersDomainConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
@Import({OrdersDomainConfiguration.class})
@ComponentScan
public class OrdersWebConfiguration {

}
