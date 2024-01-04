package com.gecko.model.dao.checkout;

import com.gecko.model.entity.CartItem;
import com.gecko.model.entity.Customer;
import com.gecko.model.entity.Order;
import com.gecko.utils.ConnectDatabase;
import jdk.internal.org.jline.utils.Colors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CheckOutDAOImpl implements CheckOutDAO{
    @Autowired
    HttpSession session;
    @Override
    public boolean CheckOut(Order order, List<CartItem> cartItems) {
        Connection connection = ConnectDatabase.openConnection();
        boolean check = false;
        try {
            connection.setAutoCommit(false);
            Customer customer = (Customer) session.getAttribute("customerLogin");
            if (customer == null){
                return false;
            }
            CallableStatement statement = connection.prepareCall("{CALL insert_orders(?,?,?)}");
            statement.setInt(1, customer.getCustomerId());
            statement.setDouble(2, order.getTotal());
            statement.setInt(3, Types.INTEGER);
            statement.executeUpdate();
            int idOder = statement.getInt(3);
            for (CartItem cartItem: cartItems) {
                CallableStatement statement2 = connection.prepareCall("{CALL insert_order_detail(?,?,?,?)}");
                statement2.setInt(1, idOder);
                statement2.setInt(2, cartItem.getProduct().getProductId());
                statement2.setInt(3, cartItem.getQuantity());
                statement2.setDouble(4, cartItem.getProduct().getPrice());
                statement2.executeUpdate();
            }
            check = true;
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return check;
    }

    @Override
    public Map<String, Integer> getQuantityProductAndOder(){
        Connection connection = ConnectDatabase.openConnection();
        Map<String, Integer> map = new HashMap<>();
        try{
            CallableStatement statement = connection.prepareCall("{CALL get_number_of_product_and_order(?,?)}");
            statement.registerOutParameter(1, Types.INTEGER);
            statement.registerOutParameter(2, Types.INTEGER);
            statement.execute();
            int quantityProduct = statement.getInt(1);
            int quantityOrder = statement.getInt(2);
            map.put("quantityProduct", quantityProduct);
            map.put("quantityOrder", quantityOrder);
        }catch (SQLException e){
            System.out.println(e);
        }
        return map;
    }
}
