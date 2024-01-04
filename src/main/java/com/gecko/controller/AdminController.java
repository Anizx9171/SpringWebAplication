package com.gecko.controller;

import com.gecko.model.entity.Customer;
import com.gecko.model.service.category.CategoryService;
import com.gecko.model.service.checkout.CheckOutService;
import com.gecko.model.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private CheckOutService checkOutService;

    @GetMapping("")
    public String index(Model model) {
        Customer customer = (Customer) httpSession.getAttribute("customerLogin");
        if (customer != null && customer.isAdmin()) {
            model.addAttribute("cateCount", categoryService.getAllCategory().size());
            model.addAttribute("cusCount", customerService.getAllCustomer().size());
            Map<String,Integer> mapCount = checkOutService.getQuantityProductAndOder();
            model.addAttribute("quantityProduct", mapCount.get("quantityProduct"));
            model.addAttribute("quantityOrder", mapCount.get("quantityOrder"));
            return "admin/index";
        }
        return "redirect:/";
    }

}
