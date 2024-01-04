package com.gecko.model.dao.customer;

import com.gecko.model.entity.Category;
import com.gecko.model.entity.Customer;
import com.gecko.utils.ConnectDatabase;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO{
    private int totalPage = 0;
    private final int LIMIT = 6;
    @Override
    public List<Customer> findAll(Integer noPage) {
        Connection connection = null;
        List<Customer> customers = new ArrayList<>();
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL pagi_customer(?,?,?)}");
            statement.setInt(1, LIMIT);
            statement.setInt(2, noPage);
            statement.setInt(3, Types.INTEGER);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getInt("id"));
                customer.setBanned(resultSet.getBoolean("banned"));
                customer.setAdmin(resultSet.getBoolean("admin"));
                customer.setCustomerName(resultSet.getString("name"));
                customer.setAddress(resultSet.getString("address"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setImageUrl(resultSet.getString("image_url"));
                customer.setEmail(resultSet.getString("email"));
                customers.add(customer);
            }
            this.totalPage = statement.getInt(3);
        } catch (SQLException e) {
            System.out.println(3);
            throw new RuntimeException(e);
        }finally {
            ConnectDatabase.closeConnection(connection);
        }
        return customers;
    }

    @Override
    public boolean create(Customer customer) {
        Connection connection = null;
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL insert_customer(?,?,?,?,?,?)}");
            statement.setString(1,customer.getCustomerName());
            statement.setString(2,customer.getEmail());
            statement.setString(3,customer.getAddress());
            statement.setString(4,customer.getPhoneNumber());
            statement.setString(5,customer.getPassword());
            statement.setString(6,customer.getImageUrl());
            int countUpdate = statement.executeUpdate();
            if (countUpdate > 0){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDatabase.closeConnection(connection);
        }
        return false;
    }

    @Override
    public Customer findByEmail(String email) {
        Connection connection = null;
        Customer customer = null;
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL find_customer_by_email(?)}");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                customer = new Customer();
                customer.setPassword(resultSet.getString("password"));
                customer.setImageUrl(resultSet.getString("image_url"));
                customer.setCustomerName(resultSet.getString("name"));
                customer.setEmail(resultSet.getString("email"));
                customer.setAddress(resultSet.getString("address"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setAdmin(resultSet.getBoolean("admin"));
                customer.setBanned(resultSet.getBoolean("banned"));
                customer.setCustomerId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(3);
            throw new RuntimeException(e);
        }finally {
            ConnectDatabase.closeConnection(connection);
        }
        return customer;
    }

    @Override
    public boolean update(Customer customer) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        Connection connection = null;
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL change_banned_customer(?)}");
            statement.setInt(1, integer);
            int countUpdate = statement.executeUpdate();
            if (countUpdate > 0){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDatabase.closeConnection(connection);
        }
        return false;
    }

    @Override
    public Customer findById(Integer integer) {
        Connection connection = null;
        Customer customer = null;
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL find_customer_by_id(?)}");
            statement.setInt(1, integer);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                customer = new Customer();
                customer.setCustomerId(resultSet.getInt("id"));
                customer.setBanned(resultSet.getBoolean("banned"));
                customer.setAdmin(resultSet.getBoolean("admin"));
                customer.setCustomerName(resultSet.getString("name"));
                customer.setAddress(resultSet.getString("address"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setImageUrl(resultSet.getString("image_url"));
                customer.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDatabase.closeConnection(connection);
        }
        return customer;
    }

    @Override
    public List<Customer> findByName(String str) {
        return null;
    }

    @Override
    public int getTotalPage() {
        return this.totalPage;
    }

    @Override
    public void changeRole(Integer id) {
        Connection connection = null;
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL change_role_customer(?)}");
            statement.setInt(1, id);
            int countUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDatabase.closeConnection(connection);
        }
    }

    @Override
    public List<Customer> getAllCustomer() {
        Connection connection = null;
        List<Customer> customers = new ArrayList<>();
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL find_all_customer()}");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getInt("id"));
                customer.setBanned(resultSet.getBoolean("banned"));
                customer.setAdmin(resultSet.getBoolean("admin"));
                customer.setCustomerName(resultSet.getString("name"));
                customer.setAddress(resultSet.getString("address"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setImageUrl(resultSet.getString("image_url"));
                customer.setEmail(resultSet.getString("email"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDatabase.closeConnection(connection);
        }
        return customers;
    }
}
