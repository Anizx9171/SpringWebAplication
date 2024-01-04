package com.gecko.model.service.order;

import com.gecko.model.entity.Order;
import com.gecko.model.entity.OrderDetail;

import java.util.List;

public interface OrderService {
    int getTotalPage();
    List<Order> findAll(int id);
    List<OrderDetail> findDetailOrder(int id);

    List<Order> findOrderByUserId(int id);
    void changeStatus(int id, int num);
    Order findById(int id);
}
