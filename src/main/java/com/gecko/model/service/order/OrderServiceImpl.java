package com.gecko.model.service.order;

import com.gecko.model.dao.order.OrderDAO;
import com.gecko.model.entity.Order;
import com.gecko.model.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderDAO orderDAO;
    @Override
    public List<Order> findAll(int noPage) {
        return orderDAO.findAll(noPage);
    }

    @Override
    public List<OrderDetail> findDetailOrder(int id) {
        return orderDAO.findDetailOrder(id);
    }

    @Override
    public List<Order> findOrderByUserId(int id) {
        return orderDAO.findOrderByUserId(id);
    }

    @Override
    public void changeStatus(int id,int num) {
        orderDAO.changeStatus(id, num);
    }

    @Override
    public int getTotalPage() {
        return orderDAO.getTotalPage();
    }

    @Override
    public Order findById(int id) {
        return orderDAO.findById(id);
    }
}
