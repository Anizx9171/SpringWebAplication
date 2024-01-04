package com.gecko.controller;

import com.gecko.model.service.category.CategoryService;
import com.gecko.model.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "user/index";
    }
    @GetMapping("/contact")
    public String contact(){
        return "user/contact";
    }
    @GetMapping("/about")
    public String about(){
        return "user/about";
    }

}
