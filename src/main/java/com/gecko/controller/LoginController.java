package com.gecko.controller;

import com.gecko.model.entity.Customer;
import com.gecko.model.service.customer.CustomerService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    CustomerService customerService;

    @GetMapping("/login")
    public String sign(Model model){
        model.addAttribute("login", new Customer());
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("login") Customer customer){
        Customer cusFind = customerService.findByEmail(customer.getEmail());
        if (cusFind != null){
            if (BCrypt.checkpw(customer.getPassword(),cusFind.getPassword())){
                if (cusFind.isBanned()){
                    return "redirect:/login?err";
                }
                httpSession.setAttribute("customerLogin", cusFind);
                if (cusFind.isAdmin()){
                    return "redirect:/admin";
                }
                    return "redirect:/";
            }
        }
        return "redirect:/login?err";
    }

    @GetMapping("/logout")
    public String logout(){
        httpSession.removeAttribute("customerLogin");
        httpSession.removeAttribute("cartItems");
        return "redirect:/";
    }
}
