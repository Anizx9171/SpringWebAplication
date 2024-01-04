package com.gecko.controller;

import com.gecko.model.service.category.CategoryService;
import com.gecko.model.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping("")
    public String shop(){
        return "redirect:/shop/1";
    }

    @GetMapping("/{noPage}")
    public String shopNoPage(Model model, @PathVariable("noPage") int page, @RequestParam(name = "search", required = false) String value) {
        if (value != null && !value.isEmpty()){
            model.addAttribute("products", productService.findByName(value));
            model.addAttribute("categories", categoryService.getAllCategory());
            model.addAttribute("totalPage", 1);
        }else {
            model.addAttribute("products", productService.findAll(page));
            model.addAttribute("categories", categoryService.getAllCategory());
            model.addAttribute("totalPage", productService.getTotalPage());
        }
         return "user/shop";
    }
    @GetMapping("/category/{catId}")
    public String showByCatalog(Model model, @PathVariable("catId") int catId) {
        model.addAttribute("products", productService.findByCategory(catId));
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("totalPage", 1);
        return "user/shop";
    }

    @GetMapping("/product-page/{id}")
    public String productDetail(Model model, @PathVariable("id") int id){
        model.addAttribute("product", productService.findById(id));
        return "user/product-page";
    }
}