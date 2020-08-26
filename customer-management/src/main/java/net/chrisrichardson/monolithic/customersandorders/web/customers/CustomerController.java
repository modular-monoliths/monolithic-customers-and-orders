package net.chrisrichardson.monolithic.customersandorders.web.customers;

import net.chrisrichardson.monolithic.customersandorders.domain.customers.Customer;
import net.chrisrichardson.monolithic.customersandorders.domain.customers.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

  private CustomerService CustomerService;

  @Autowired
  public CustomerController(CustomerService CustomerService) {
    this.CustomerService = CustomerService;
  }


  @RequestMapping(value = "/customers", method = RequestMethod.POST)
  public CreateCustomerResponse createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
    Customer customer = CustomerService.createCustomer(createCustomerRequest.getName(), createCustomerRequest.getCreditLimit());
    return new CreateCustomerResponse(customer.getId());
  }

}
