package net.chrisrichardson.monolithic.customersandorders.web.orderhistory;

import net.chrisrichardson.monolithic.customersandorders.domain.money.Money;

public class OrderView {

  private Long id;
  private String state;
  private Money orderTotal;

  public OrderView() {
  }

  public OrderView(Long id, String state, Money orderTotal) {
    this.id = id;
    this.orderTotal = orderTotal;
    this.state = state;
  }

  public Long getId() {
    return id;
  }

  public Money getOrderTotal() {
    return orderTotal;
  }

  public String getState() {
    return state;
  }

}
