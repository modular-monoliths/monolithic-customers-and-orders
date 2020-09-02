package net.chrisrichardson.monolithic.customersandorders.domain.customers.api;

import net.chrisrichardson.monolithic.customersandorders.domain.common.Money;

public class CustomerDto {
  private long id;
  private String name;
  private Money creditLimit;


  public CustomerDto(long id, String name, Money creditLimit) {
    this.id = id;
    this.name = name;
    this.creditLimit = creditLimit;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Money getCreditLimit() {
    return creditLimit;
  }
}
