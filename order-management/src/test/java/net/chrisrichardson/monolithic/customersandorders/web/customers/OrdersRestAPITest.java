package net.chrisrichardson.monolithic.customersandorders.web.customers;

import io.restassured.response.ValidatableResponse;
import net.chrisrichardson.monolithic.customersandorders.domain.money.Money;
import net.chrisrichardson.monolithic.customersandorders.domain.customers.api.AvailableCreditManagement;
import net.chrisrichardson.monolithic.customersandorders.web.orders.OrdersWebConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.util.Collections.singletonMap;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes= OrdersRestAPITest.Config.class)
public class OrdersRestAPITest {

  @Configuration
  @Import(OrdersWebConfiguration.class)
  @EnableAutoConfiguration
  public static class Config {
  }

  @LocalServerPort
  private int port;

  @MockBean
  private AvailableCreditManagement availableCreditManagement;

  private String hostName = "localhost";

  private String baseUrl(String... path) {
    StringBuilder sb = new StringBuilder();
    sb.append("http://").append(hostName).append(":").append(port);
    Arrays.stream(path).forEach(p -> {
      sb.append('/').append(p);
    });
    return sb.toString();
  }

  private long customerId = 101L;
  private BigDecimal orderTotal = new BigDecimal("12.34");

  @Test
  public void shouldCreateOrder() {
    long orderId = createOrder(customerId, orderTotal);

    assertOrderState(orderId, "APPROVED");

    Mockito.verify(availableCreditManagement).reserveCredit(customerId, new Money(orderTotal));

  }

  private long createOrder(long customerId, BigDecimal orderTotal) {
    return createOrderExpecting(customerId, orderTotal, HttpStatus.OK).
            extract().<Integer>path("orderId");
  }
  private ValidatableResponse createOrderExpecting(long customerId, BigDecimal orderTotal, HttpStatus expectedStatus) {
    Map<String, Object> jsonAsMap = new HashMap<>();
    jsonAsMap.put("customerId", customerId);
    jsonAsMap.put("orderTotal", singletonMap("amount", orderTotal));
    return given().
            contentType(JSON).
            body(jsonAsMap).
            when().
            post(baseUrl("orders")).
            then().
            statusCode(expectedStatus.value())
            ;
  }

  private void assertOrderState(long orderId, String expectedState) {

    given().
            contentType(JSON).
            when().
            get(baseUrl("orders", Long.toString(orderId))).
            then().
            statusCode(200).
            body("orderState", equalTo(expectedState))
    ;
  }


}
