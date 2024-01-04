package com.gecko.controller;

import com.gecko.model.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/{noPage}")
    public String customers(@PathVariable("noPage") Integer noPage,Model model){
        if (noPage == null){
            noPage = 1;
        }
        model.addAttribute("customers", customerService.findAll(noPage));
        model.addAttribute("totalPage", customerService.getTotalPage());
        return "admin/customer/customers";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id){
        customerService.changeRole(id);
        return "redirect:/admin/customer/1";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        customerService.delete(id);
        return "redirect:/admin/customer/1";
    }
    @GetMapping("")
    public String index(){
        return "redirect:/admin/customer/1";
    }
}
