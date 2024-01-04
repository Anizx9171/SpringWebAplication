package com.gecko.controller;

import com.gecko.model.dao.customer.CustomerDAO;
import com.gecko.model.entity.Order;
import com.gecko.model.entity.OrderDetail;
import com.gecko.model.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    CustomerDAO customerDAO;
    @GetMapping("/{noPage}")
    public String index(@PathVariable("noPage") int noPage, Model model){
        model.addAttribute("orders", orderService.findAll(noPage));
        model.addAttribute("totalPage", orderService.getTotalPage());
        return "admin/order/order";
    }
    @GetMapping("")
    public String navigateIndex(){
        return "redirect:/admin/order/1";
    }

    @RequestMapping("/edit/{orderId}")
    public String edit(@PathVariable("orderId") int orderId, Model model) {
        Order order = orderService.findById(orderId);
        if (order != null) {
            model.addAttribute("order", order);
            model.addAttribute("order_details", orderService.findDetailOrder(order.getOrderId()));
            model.addAttribute("customer", customerDAO.findById(order.getCustomerId()));
            return "admin/order/edit";
        }
        return "redirect:/admin/order/1";
    }

    @RequestMapping("/update")
    public String update(@RequestParam("sta") int sta, @RequestParam("orderId") int orderId){
        orderService.changeStatus(orderId, sta);
        return "redirect:/admin/order/1";
    }

}
