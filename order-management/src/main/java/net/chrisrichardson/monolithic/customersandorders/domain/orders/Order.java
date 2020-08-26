package net.chrisrichardson.monolithic.customersandorders.domain.orders;


import net.chrisrichardson.monolithic.customersandorders.domain.money.Money;

import javax.persistence.*;

@Entity
@Table(name="orders")
@Access(AccessType.FIELD)
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private OrderState state;

  private long customerId;

  @Embedded
  private Money orderTotal;

  @Version
  private Long version;

  private Order() {
  }

  public Order(long customerId, Money orderTotal) {
    this.customerId = customerId;
    this.orderTotal = orderTotal;
    this.state = OrderState.APPROVED;
  }


  public static Order createOrder(Money orderTotal, long customerId) {
    Order order = new Order(customerId, orderTotal);
    return order;
  }

  public Long getId() {
    return id;
  }

  public OrderState getState() {
    return state;
  }

  public long getCustomerId() {
    return customerId;
  }

  public Money getOrderTotal() {
    return orderTotal;
  }

  public Long getVersion() {
    return version;
  }

  public void cancel() {
    if (state == OrderState.APPROVED) {
      this.state = OrderState.CANCELLED;
      return;
    }
    throw new UnsupportedOperationException("Can't cancel in this state: " + state);
  }
}
