package net.chrisrichardson.monolithic.customersandorders.web.orderhistory;

import net.chrisrichardson.monolithic.customersandorders.domain.money.Money;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerView {

  private Long id;

  private Map<Long, OrderView> orders = new HashMap<>();
  private String name;
  private Money creditLimit;

  public CustomerView(String name, Money creditLimit, List<OrderView> orders) {
    this.name = name;
    this.creditLimit = creditLimit;
    this.orders = orders.stream().collect(Collectors.toMap(OrderView::getId, x -> x));
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public Map<Long, OrderView> getOrders() {
    return orders;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setCreditLimit(Money creditLimit) {
    this.creditLimit = creditLimit;
  }

  public Money getCreditLimit() {
    return creditLimit;
  }
}
