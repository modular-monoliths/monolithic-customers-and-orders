package net.chrisrichardson.monolithic.customersandorders.web.customers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.util.Collections.singletonMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes=CustomersIntegrationTest.Config.class)
public class CustomersIntegrationTest {

  @Configuration
  @Import(CustomersWebConfiguration.class)
  @EnableAutoConfiguration
  public static class Config {
  }

  @LocalServerPort
  private int port;

  private String hostName = "localhost";

  private String baseUrl(String... path) {
    StringBuilder sb = new StringBuilder();
    sb.append("http://").append(hostName).append(":").append(port);
    Arrays.stream(path).forEach(p -> {
      sb.append('/').append(p);
    });
    return sb.toString();
  }

  @Test
  public void shouldCreateCustomer() {
    long customerId = createCustomer("Fred", new BigDecimal("15.00"));
  }

  private long createCustomer(String name, BigDecimal creditLimit) {
    Map<String, Object> jsonAsMap = new HashMap<>();
    jsonAsMap.put("name", name);
    jsonAsMap.put("creditLimit", singletonMap("amount", creditLimit));
    Integer customerId = given().
            contentType(JSON).
            body(jsonAsMap).
            when().
            post(baseUrl("customers")).
            then().
            statusCode(200).
            extract().
            path("customerId")
            ;

    return customerId;
  }

}
