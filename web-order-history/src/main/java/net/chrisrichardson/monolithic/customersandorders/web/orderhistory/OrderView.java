package net.chrisrichardson.monolithic.customersandorders.web.orderhistory;

import net.chrisrichardson.monolithic.customersandorders.domain.common.Money;
import net.chrisrichardson.monolithic.customersandorders.domain.orders.OrderState;

public class OrderView {

  private Long id;
  private OrderState state;
  private Money orderTotal;

  public OrderView() {
  }

  public OrderView(Long id, OrderState state, Money orderTotal) {
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

  public OrderState getState() {
    return state;
  }

}
