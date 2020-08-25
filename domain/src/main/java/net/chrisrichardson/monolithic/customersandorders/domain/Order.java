package net.chrisrichardson.monolithic.customersandorders.domain;


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

  @ManyToOne
  private Customer customer;

  @Embedded
  private Money orderTotal;

  @Version
  private Long version;

  private Order() {
  }

  public Order(Customer customer, Money orderTotal) {
    this.customer = customer;
    this.orderTotal = orderTotal;
    this.state = OrderState.APPROVED;
  }


  public static Order createOrder(Money orderTotal, Customer customer) {
    Order order = new Order(customer, orderTotal);
    return order;
  }

  public Long getId() {
    return id;
  }

  public OrderState getState() {
    return state;
  }

  public Customer getCustomer() {
    return customer;
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
      customer.unreserveCredit(orderTotal);
      return;
    }
    throw new UnsupportedOperationException("Can't cancel in this state: " + state);
  }
}
