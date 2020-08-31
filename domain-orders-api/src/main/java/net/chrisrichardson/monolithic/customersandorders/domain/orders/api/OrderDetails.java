package net.chrisrichardson.monolithic.customersandorders.domain.orders.api;

import net.chrisrichardson.monolithic.customersandorders.domain.common.Money;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class OrderDetails {

  private Long customerId;

  @Embedded
  private Money orderTotal;

  public OrderDetails() {
  }

  public OrderDetails(Long customerId, Money orderTotal) {
    this.customerId = customerId;
    this.orderTotal = orderTotal;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public Money getOrderTotal() {
    return orderTotal;
  }
}
