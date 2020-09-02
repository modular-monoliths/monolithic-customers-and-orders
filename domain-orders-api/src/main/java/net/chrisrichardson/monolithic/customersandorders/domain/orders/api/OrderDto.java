package net.chrisrichardson.monolithic.customersandorders.domain.orders.api;

import net.chrisrichardson.monolithic.customersandorders.domain.common.Money;

public class OrderDto {
  private long id;
  private String state;
  private Money orderTotal;


  public OrderDto(long id, String state) {
    this.id = id;
    this.state = state;
  }

  public long getId() {
    return id;
  }

  public String getState() {
    return state;
  }

  public Money getOrderTotal() {
    return orderTotal;
  }
}
