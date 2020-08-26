package net.chrisrichardson.monolithic.customersandorders.web.orderhistory;

import net.chrisrichardson.monolithic.customersandorders.domain.money.Money;
import net.chrisrichardson.monolithic.customersandorders.domain.customers.api.CustomerDetails;
import net.chrisrichardson.monolithic.customersandorders.domain.customers.api.CustomerDetailsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes= OrderHistoryWebConfiguration.class)
@EnableAutoConfiguration
public class OrderHistoryRestApiTest {

  private String hostName = "localhost";

  @LocalServerPort
  private int port;

  private String baseUrl(String... path) {
    StringBuilder sb = new StringBuilder();
    sb.append("http://").append(hostName).append(":").append(port);
    Arrays.stream(path).forEach(p -> {
      sb.append('/').append(p);
    });
    return sb.toString();
  }

  private long customerId = 101L;
  private long orderId = 102L;

  @MockBean
  private CustomerDetailsRepository customerDetailsRepository;

  @MockBean
  private OrderViewRepository orderViewRepository;

  @Test
  public void shouldReturnOrderHistory() {

    when(customerDetailsRepository.findById(customerId))
            .thenReturn(Optional.of(new CustomerDetails("Fred", new Money("12.34"))));
    when(orderViewRepository.findByCustomerId(customerId))
            .thenReturn(Collections.singletonList(new OrderView(orderId, "APPROVED", new Money("12.34"))));

    given().
            contentType(JSON).
            when().
            get(baseUrl("customers", Long.toString(customerId))).
            then().
            statusCode(200).
            body(String.format("orders['%s'].state", orderId), equalTo("APPROVED"))
    ;

    verify(customerDetailsRepository).findById(customerId);
    verify(orderViewRepository).findByCustomerId(customerId);

  }

}