package net.chrisrichardson.monolithic.customersandorders.web.orders;


import net.chrisrichardson.monolithic.customersandorders.domain.orders.OrderState;

public class GetOrderResponse {
  private Long orderId;
  private OrderState orderState;

  public GetOrderResponse() {
  }

  public GetOrderResponse(Long orderId, OrderState orderState) {
    this.orderId = orderId;
    this.orderState = orderState;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public OrderState getOrderState() {
    return orderState;
  }

  public void setOrderState(OrderState orderState) {
    this.orderState = orderState;
  }
}
