package com.gecko.model.dao.order;

import com.gecko.model.entity.Order;
import com.gecko.model.entity.OrderDetail;

import java.util.List;

public interface OrderDAO {
    int getTotalPage();
    List<Order> findAll(int id);
    List<OrderDetail> findDetailOrder(int id);

    List<Order> findOrderByUserId(int id);
    void changeStatus(int id, int num);
    Order findById(int id);
}
