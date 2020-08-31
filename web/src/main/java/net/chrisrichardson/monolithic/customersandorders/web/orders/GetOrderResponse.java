package net.chrisrichardson.monolithic.customersandorders.web.orders;


public class GetOrderResponse {
  private Long orderId;
  private String orderState;

  public GetOrderResponse(Long orderId, String orderState) {
    this.orderId = orderId;
    this.orderState = orderState;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public String getOrderState() {
    return orderState;
  }

  public void setOrderState(String orderState) {
    this.orderState = orderState;
  }
}
