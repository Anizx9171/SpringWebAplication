package com.gecko.model.entity;


import java.util.List;

public class HistoryOrder {
    Order order;
    List<OrderDetail> orderDetails;

    public HistoryOrder() {
    }

    public HistoryOrder(Order order, List<OrderDetail> orderDetails) {
        this.order = order;
        this.orderDetails = orderDetails;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
