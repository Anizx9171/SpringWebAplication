package com.gecko.controller;

import com.gecko.model.dao.checkout.CheckOutDAO;
import com.gecko.model.entity.CartItem;
import com.gecko.model.entity.Customer;
import com.gecko.model.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckOutController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    CheckOutDAO checkOutDAO;

    @PostMapping("")
    public String indexCheckout(@RequestParam("total") Double total){
        Customer customer = (Customer) httpSession.getAttribute("customerLogin");
        List<CartItem> cartItems = (List<CartItem>) httpSession.getAttribute("cartItems");
        if (cartItems != null){
            if (!cartItems.isEmpty()){
                Order order = new Order();
                order.setCustomerId(customer.getCustomerId());
                order.setTotal(total);
                if (checkOutDAO.CheckOut(order, cartItems)){
                    httpSession.removeAttribute("cartItems");
                    return "redirect:/history";
                }
            }
            return "redirect:/cart?err";
        }
        return "redirect:/cart";
    }
}
