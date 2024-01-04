package com.gecko.model.service.checkout;

import com.gecko.model.dao.checkout.CheckOutDAO;
import com.gecko.model.entity.CartItem;
import com.gecko.model.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CheckOutServiceImpl implements CheckOutService {
    @Autowired
    CheckOutDAO checkOutDAO;
    @Override
    public boolean CheckOut(Order order, List<CartItem> cartItems) {
        return checkOutDAO.CheckOut(order, cartItems);
    }

    @Override
    public Map<String, Integer> getQuantityProductAndOder() {
        return checkOutDAO.getQuantityProductAndOder();
    }
}
