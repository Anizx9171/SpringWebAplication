package com.gecko.controller;

import com.gecko.model.entity.Customer;
import com.gecko.model.service.customer.CustomerService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@PropertySource("classpath:config.properties")
public class RegisterController {
    @Value("${path}")
    private String path;
    @Autowired
    CustomerService customerService;
    @Autowired
    HttpSession httpSession;

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("customer", new Customer());
        return "user/register";
    }
    @PostMapping("/register")
    public String createAccount(@ModelAttribute("customer") Customer customer, @RequestParam("img") MultipartFile file){
        String fileName = file.getOriginalFilename();
        File destination = new File(path + fileName);
        try {
            customer.setImageUrl(fileName);
            if (customerService.create(customer)){
                file.transferTo(destination);
                return "redirect:/login";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/register?err";
    }
}