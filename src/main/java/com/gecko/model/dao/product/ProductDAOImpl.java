package com.gecko.model.dao.product;

import com.gecko.model.dao.category.CategoryDAO;
import com.gecko.model.entity.Category;
import com.gecko.model.entity.Product;
import com.gecko.utils.ConnectDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ProductDAOImpl implements ProductDAO{
    @Autowired
    CategoryDAO categoryDAO;
    private final int LIMIT = 6;
    private int totalPage = 0;

    @Override
    public List<Product> findAll(Integer noPage) {
        Connection connection = null;
        List<Product> products = new ArrayList<>();
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL pagi_product(?,?,?)}");
            statement.setInt(1, LIMIT);
            statement.setInt(2, noPage);
            statement.setInt(3, Types.INTEGER);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Product product = new Product();
                product.setProductId(resultSet.getInt("id"));
                Category prCat = categoryDAO.findById(resultSet.getInt("category_id"));
                if (prCat == null) {
                    throw new NullPointerException("Category not found");
                }
                product.setCategory(prCat);
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getDouble("price"));
                product.setProductName(resultSet.getString("name"));
                product.setImageUrl(resultSet.getString("image_url"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setStatus(resultSet.getBoolean("status"));
                products.add(product);
            }
            this.totalPage = statement.getInt(3);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDatabase.closeConnection(connection);
        }
        return products;
    }

    @Override
    public boolean create(Product product) {
        Connection connection = null;
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL insert_product(?,?,?,?,?,?)}");
            statement.setInt(1, product.getCategory().getCategoryId());
            statement.setString(2, product.getProductName());
            statement.setString(3, product.getDescription());
            statement.setDouble(4,product.getPrice());
            statement.setInt(5, product.getQuantity());
            statement.setString(6,product.getImageUrl());
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
    public boolean update(Product product) {
        Connection connection = null;
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL update_product(?,?,?,?,?,?,?)}");
            statement.setInt(1, product.getProductId());
            statement.setInt(2, product.getCategory().getCategoryId());
            statement.setString(3, product.getProductName());
            statement.setString(4, product.getDescription());
            statement.setDouble(5,product.getPrice());
            statement.setInt(6, product.getQuantity());
            statement.setString(7,product.getImageUrl());
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
    public boolean delete(Integer integer) {
        Connection connection = null;
        connection = ConnectDatabase.openConnection();
        try {
            CallableStatement statement = connection.prepareCall("{CALL change_status_product(?)}");
            statement.setInt(1, integer);
            if (statement.executeUpdate() > 0){
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
    public Product findById(Integer integer) {
        Connection connection = null;
        Product product = null;
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL find_product_by_id(?)}");
            statement.setInt(1, integer);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                product = new Product();
                product.setProductId(resultSet.getInt("id"));
                Category prCat = categoryDAO.findById(resultSet.getInt("category_id"));
                if (prCat == null) {
                    throw new NullPointerException("Category not found");
                }
                product.setCategory(prCat);
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getDouble("price"));
                product.setProductName(resultSet.getString("name"));
                product.setImageUrl(resultSet.getString("image_url"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setStatus(resultSet.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDatabase.closeConnection(connection);
        }
        return product;
    }

    @Override
    public List<Product> findByName(String str) {
        Connection connection = null;
        List<Product> products = new ArrayList<>();
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL find_product_by_name(?)}");
            statement.setString(1,"%" + str + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Product product = new Product();
                product.setProductId(resultSet.getInt("id"));
                Category prCat = categoryDAO.findById(resultSet.getInt("category_id"));
                if (prCat == null) {
                    throw new NullPointerException("Category not found");
                }
                product.setCategory(prCat);
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getDouble("price"));
                product.setProductName(resultSet.getString("name"));
                product.setImageUrl(resultSet.getString("image_url"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setStatus(resultSet.getBoolean("status"));
                products.add(product);
            }
            this.totalPage = 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDatabase.closeConnection(connection);
        }
        return products;
    }

    @Override
    public int getTotalPage() {
        return this.totalPage;
    }

    @Override
    public List<Product> getAllProduct() {
        return null;
    }

    @Override
    public List<Product> findByCategory(Integer catId) {
        Connection connection = null;
        List<Product> products = new ArrayList<>();
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL find_product_by_cat_id(?)}");
            statement.setInt(1, catId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Product product = new Product();
                product.setProductId(resultSet.getInt("id"));
                Category prCat = categoryDAO.findById(resultSet.getInt("category_id"));
                if (prCat == null) {
                    throw new NullPointerException("Category not found");
                }
                product.setCategory(prCat);
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getDouble("price"));
                product.setProductName(resultSet.getString("name"));
                product.setImageUrl(resultSet.getString("image_url"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setStatus(resultSet.getBoolean("status"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDatabase.closeConnection(connection);
        }
        return products;
    }
}
