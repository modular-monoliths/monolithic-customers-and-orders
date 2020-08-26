package net.chrisrichardson.monolithic.customersandorders.domain.customers;

import net.chrisrichardson.monolithic.customersandorders.domain.money.Money;
import net.chrisrichardson.monolithic.customersandorders.domain.customers.api.CustomerCreditLimitExceededException;

import javax.persistence.*;

@Entity
@Table(name="Customer")
@Access(AccessType.FIELD)
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @Embedded
  @AttributeOverrides({
          @AttributeOverride(name = "amount",
                  column = @Column(name = "credit_limit"))
  })
  private Money creditLimit;

  @Embedded
  @AttributeOverrides({
          @AttributeOverride(name = "amount",
                  column = @Column(name = "available_credit"))
  })
  private Money availableCredit;

  private Long creationTime;

  @Version
  private Long version;

  public Customer() {
  }

  public Customer(String name, Money creditLimit) {
    this.name = name;
    this.creditLimit = creditLimit;
    this.availableCredit = creditLimit;
    this.creationTime = System.currentTimeMillis();
  }

  public static Customer create(String name, Money creditLimit) {
    Customer customer = new Customer(name, creditLimit);
    return customer;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Money getCreditLimit() {
    return creditLimit;
  }

  public void reserveCredit(Money orderTotal) {
    if (availableCredit.isGreaterThanOrEqual(orderTotal)) {
      availableCredit = availableCredit.subtract(orderTotal);
    } else
      throw new CustomerCreditLimitExceededException();
  }

  public void unreserveCredit(Money orderTotal) {
    availableCredit = availableCredit.add(orderTotal);
  }
}
