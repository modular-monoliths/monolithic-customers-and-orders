package net.chrisrichardson.monolithic.customersandorders.web.orders;

import net.chrisrichardson.monolithic.customersandorders.domain.Money;
import net.chrisrichardson.monolithic.customersandorders.domain.OrderState;

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
