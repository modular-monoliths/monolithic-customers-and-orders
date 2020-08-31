package net.chrisrichardson.monolithic.customersandorders.domain.customers.api;

public class CustomerDto {
  private long id;

  public CustomerDto(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }
}
