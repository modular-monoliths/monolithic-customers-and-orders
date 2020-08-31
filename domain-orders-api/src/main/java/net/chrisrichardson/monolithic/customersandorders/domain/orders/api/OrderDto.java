package net.chrisrichardson.monolithic.customersandorders.domain.orders.api;

public class OrderDto {
  private long id;
  private String state;

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
}
