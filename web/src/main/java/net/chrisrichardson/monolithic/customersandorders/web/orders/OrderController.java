package net.chrisrichardson.monolithic.customersandorders.web.orders;

import net.chrisrichardson.monolithic.customersandorders.domain.customers.CustomerCreditLimitExceededException;
import net.chrisrichardson.monolithic.customersandorders.domain.orders.OrderState;
import net.chrisrichardson.monolithic.customersandorders.domain.orders.api.OrderDetails;
import net.chrisrichardson.monolithic.customersandorders.domain.orders.OrderRepository;
import net.chrisrichardson.monolithic.customersandorders.domain.orders.api.OrderDto;
import net.chrisrichardson.monolithic.customersandorders.domain.orders.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

  private OrderService orderService;
  private OrderRepository orderRepository;

  @Autowired
  public OrderController(OrderService orderService,
                         OrderRepository orderRepository) {

    this.orderService = orderService;
    this.orderRepository = orderRepository;
  }

  @RequestMapping(value = "/orders", method = RequestMethod.POST)
  public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
    try {
      OrderDto order = orderService.createOrder(new OrderDetails(createOrderRequest.getCustomerId(), createOrderRequest.getOrderTotal()));
      return ResponseEntity.ok(new CreateOrderResponse(order.getId()));
    } catch (ObjectRetrievalFailureException e) {
      return ResponseEntity.notFound().build();
    } catch (CustomerCreditLimitExceededException e) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
  }

  @RequestMapping(value="/orders/{orderId}", method= RequestMethod.GET)
  public ResponseEntity<GetOrderResponse> getOrder(@PathVariable Long orderId) {
     return orderRepository
            .findById(orderId)
            .map(order -> makeSuccessfulResponse(order.getId(), order.getState().name()))
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @RequestMapping(value="/orders/{orderId}/cancel", method= RequestMethod.POST)
  public ResponseEntity<GetOrderResponse> cancelOrder(@PathVariable Long orderId) {
     OrderDto order = orderService.cancelOrder(orderId);
     return makeSuccessfulResponse(order.getId(), order.getState());
  }

  private ResponseEntity<GetOrderResponse> makeSuccessfulResponse(Long orderId, String state) {
    return new ResponseEntity<>(new GetOrderResponse(orderId, state), HttpStatus.OK);
  }
}