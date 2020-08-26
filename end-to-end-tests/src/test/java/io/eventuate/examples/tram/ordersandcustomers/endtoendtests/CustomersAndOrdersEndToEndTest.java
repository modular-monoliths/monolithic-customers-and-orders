package io.eventuate.examples.tram.ordersandcustomers.endtoendtests;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.util.Collections.singletonMap;
import static org.hamcrest.Matchers.equalTo;

;

public class CustomersAndOrdersEndToEndTest {

  private String hostName = "localhost";

  private String baseUrl(String... path) {
    StringBuilder sb = new StringBuilder();
    sb.append("http://").append(hostName).append(":8080");
    Arrays.stream(path).forEach(p -> {
      sb.append('/').append(p);
    });
    return sb.toString();
  }


  @Test
  public void shouldApprove() {
    long customerId = createCustomer("Fred", new BigDecimal("15.00"));
    long orderId = createOrder(customerId, new BigDecimal("12.34"));
    assertOrderState(orderId, "APPROVED");
  }

  @Test
  public void shouldApproveInOrderHistory() {
    long customerId = createCustomer("Fred", new BigDecimal("15.00"));
    long orderId = createOrder(customerId, new BigDecimal("12.34"));
    assertStateInOrderHistory(customerId, orderId, "APPROVED");
  }

  @Test
  public void shouldReject() {
    long customerId = createCustomer("Fred", new BigDecimal("15.00"));
    createOrderExpecting(customerId, new BigDecimal("123.34"), HttpStatus.CONFLICT);
  }

  @Test
  public void shouldRejectForNonExistentCustomerId() {
    long customerId = System.nanoTime();
    createOrderExpecting(customerId, new BigDecimal("123.34"), HttpStatus.NOT_FOUND);
  }

  @Test
  public void shouldCancel() {
    long customerId = createCustomer("Fred", new BigDecimal("15.00"));
    long orderId = createOrder(customerId, new BigDecimal("12.34"));
    assertOrderState(orderId, "APPROVED");
    cancelOrder(orderId);
    assertOrderState(orderId, "CANCELLED");

    /*
    Eventually.eventually(100, 400, TimeUnit.MILLISECONDS, () -> {
      CustomerView customerView = getCustomerView(customerId);
      Map<Long, OrderInfo> orders = customerView.getOrders();
      assertThat(orders.get(orderId).getState(), is("CANCELLED"));
    });
    */
  }

  /*
  @Test
  public void shouldRejectApproveAndKeepOrdersInHistory() {
    Long customerId = createCustomer("John", new BigDecimal("1000"));

    Long order1Id = createOrder(customerId, new BigDecimal("100"));

    assertOrderState(order1Id, OrderState.APPROVED);

    Long order2Id = createOrder(customerId, new BigDecimal("1000"));

    assertOrderState(order2Id, OrderState.REJECTED);


    Eventually.eventually(100, 400, TimeUnit.MILLISECONDS, () -> {
      CustomerView customerView = getCustomerView(customerId);

      Map<Long, OrderInfo> orders = customerView.getOrders();

      assertEquals(2, orders.size());

      assertThat(orders.get(order1Id).getState(), is(OrderState.APPROVED));
      assertThat(orders.get(order2Id).getState(), is(OrderState.REJECTED));
    });
  }

  private CustomerView getCustomerView(Long customerId) {
    String customerHistoryUrl = baseUrlOrderHistory("customers") + "/" + customerId;
    ResponseEntity<CustomerView> response = restTemplate.getForEntity(customerHistoryUrl, CustomerView.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());

    Assert.assertNotNull(response);

    return response.getBody();
  }
  */


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

  private void cancelOrder(long orderId) {
    given().
            when().
            post(baseUrl("orders", Long.toString(orderId), "cancel")).
            then().
            statusCode(200).
            extract()
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

  private void assertStateInOrderHistory(long customerId, long orderId, String expectedState) {
    given().
            contentType(JSON).
            when().
            get(baseUrl("customers", Long.toString(customerId))).
            then().
            statusCode(200).
            body(String.format("orders['%s'].state", orderId), equalTo(expectedState))
    ;
  }



}
