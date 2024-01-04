package com.gecko.model.dao.category;

import com.gecko.model.entity.Category;
import com.gecko.utils.ConnectDatabase;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDAO{
    private final int LIMIT = 6;
    private int totalPage = 0;

    @Override
    public List<Category> getAllCategory() {
        Connection connection = null;
        List<Category> categories = new ArrayList<>();
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL find_all_category()}");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("id"));
                category.setCategoryName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("description"));
                category.setStatus(resultSet.getBoolean("status"));
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDatabase.closeConnection(connection);
        }
        return categories;
    }

    @Override
    public List<Category> findAll(Integer noPage) {
        Connection connection = null;
        List<Category> categories = new ArrayList<>();
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL pagi_category(?,?,?)}");
            statement.setInt(1, LIMIT);
            statement.setInt(2, noPage);
            statement.setInt(3, Types.INTEGER);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("id"));
                category.setCategoryName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("description"));
                category.setStatus(resultSet.getBoolean("status"));
                categories.add(category);
            }
            this.totalPage = statement.getInt(3);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDatabase.closeConnection(connection);
        }
        return categories;
    }

    @Override
    public boolean create(Category category) {
        Connection connection = null;
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL insert_category(?,?)}");
            statement.setString(1,category.getCategoryName());
            statement.setString(2,category.getDescription());
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
    public boolean update(Category category) {
        Connection connection = null;
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL update_category(?,?,?)}");
            statement.setInt(1,category.getCategoryId());
            statement.setString(2,category.getCategoryName());
            statement.setString(3,category.getDescription());
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
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL change_category_status(?)}");
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
    public Category findById(Integer integer) {
        Connection connection = null;
        Category category = null;
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL find_category_by_id(?)}");
            statement.setInt(1, integer);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                category = new Category();
                category.setCategoryId(resultSet.getInt("id"));
                category.setCategoryName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("description"));
                category.setStatus(resultSet.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDatabase.closeConnection(connection);
        }
        return category;
    }

    @Override
    public List<Category> findByName(String str) {
        Connection connection = null;
        List<Category> categories = new ArrayList<>();
        try {
            connection = ConnectDatabase.openConnection();
            CallableStatement statement = connection.prepareCall("{CALL find_category_by_name(?)}");
            statement.setString(1,"%" + str + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("id"));
                category.setCategoryName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("description"));
                category.setStatus(resultSet.getBoolean("status"));
                categories.add(category);
            }
            this.totalPage = 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDatabase.closeConnection(connection);
        }
        return categories;
    }

    @Override
    public int getTotalPage() {
        return this.totalPage;
    }

}