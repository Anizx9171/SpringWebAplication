package com.gecko.model.dao.order;

import com.gecko.model.entity.Category;
import com.gecko.model.entity.Order;
import com.gecko.model.entity.OrderDetail;
import com.gecko.model.service.product.ProductService;
import com.gecko.utils.ConnectDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class OrderDAOImpl implements OrderDAO {
    @Autowired
    ProductService productService;
    private final int LIMIT = 6;
    private int totalPage = 0;

    @Override
    public List<Order> findAll(int noPage) {
        Connection connection = null;
        List<Order> orders = new ArrayList<>();
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL pagi_order(?,?,?)}");
            statement.setInt(1, LIMIT);
            statement.setInt(2, noPage);
            statement.setInt(3, Types.INTEGER);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Order order = new Order();
                order.setTotal(resultSet.getDouble("total"));
                order.setOrderId(resultSet.getInt("id"));
                order.setCustomerId(resultSet.getInt("customer_id"));
                order.setStatus(resultSet.getInt("status"));
                orders.add(order);
            }
            this.totalPage = statement.getInt(3);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDatabase.closeConnection(connection);
        }
        return orders;
    }

    @Override
    public List<OrderDetail> findDetailOrder(int id) {
        Connection connection = null;
        List<OrderDetail> orderDetails = new ArrayList<>();
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL get_order_detail_by_order_id(?)}");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(resultSet.getInt("order_id"));
                orderDetail.setProduct(productService.findById(resultSet.getInt("product_id")));
                orderDetail.setPrice(resultSet.getDouble("price"));
                orderDetail.setQuantity(resultSet.getInt("quantity"));
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDatabase.closeConnection(connection);
        }
        return orderDetails;
    }

    @Override
    public List<Order> findOrderByUserId(int id) {
        Connection connection = null;
        List<Order> orders = new ArrayList<>();
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL get_order_by_customer_id(?)}");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Order order = new Order();
                order.setTotal(resultSet.getDouble("total"));
                order.setOrderId(resultSet.getInt("id"));
                order.setCustomerId(resultSet.getInt("customer_id"));
                order.setStatus(resultSet.getInt("status"));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDatabase.closeConnection(connection);
        }
        return orders;
    }

    @Override
    public void changeStatus(int id,int num) {
        Connection connection = null;
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL change_status_order(?,?)}");
            statement.setInt(1, id);
            statement.setInt(2, num);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDatabase.closeConnection(connection);
        }
    }

    @Override
    public int getTotalPage() {
        return this.totalPage;
    }

    @Override
    public Order findById(int id) {
        Connection connection = null;
        Order order = null;
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL get_order_by_id(?)}");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = new Order();
                order.setTotal(resultSet.getDouble("total"));
                order.setOrderId(resultSet.getInt("id"));
                order.setCustomerId(resultSet.getInt("customer_id"));
                order.setStatus(resultSet.getInt("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDatabase.closeConnection(connection);
        }
        return order;
    }

}
