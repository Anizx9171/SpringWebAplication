package com.gecko.controller;

import com.gecko.model.entity.HistoryOrder;
import com.gecko.model.dao.order.OrderDAO;
import com.gecko.model.entity.Customer;
import com.gecko.model.entity.Order;
import com.gecko.model.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    HttpSession session;
    @Autowired
    OrderService orderService;

    @RequestMapping("")
    public String getHistory(Model model){
        Customer customer = (Customer) session.getAttribute("customerLogin");
        if (customer != null){
            List<Order> orders = orderDAO.findOrderByUserId(customer.getCustomerId());
            List<HistoryOrder> orderList = new ArrayList<>();
            for (Order order: orders) {
                HistoryOrder historyOrder = new HistoryOrder();
                historyOrder.setOrder(order);
                historyOrder.setOrderDetails(orderDAO.findDetailOrder(order.getOrderId()));
                orderList.add(historyOrder);
            }
            model.addAttribute("orderList", orderList);
            return "user/history-page";
        }
        return "redirect:/login";
    }

    @RequestMapping("/cancel/{orderId}")
    public String update(@PathVariable("orderId") int orderId){
        orderService.changeStatus(orderId, 0);
        return "redirect:/history";
    }
}
