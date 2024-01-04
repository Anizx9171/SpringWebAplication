package com.gecko.model.dao.checkout;

import com.gecko.model.entity.CartItem;
import com.gecko.model.entity.Order;

import java.util.List;
import java.util.Map;

public interface CheckOutDAO {
    boolean CheckOut(Order order, List<CartItem> cartItems);
    Map<String, Integer> getQuantityProductAndOder();
}
