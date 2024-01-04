package com.gecko.controller;

import com.gecko.model.entity.Product;
import com.gecko.model.service.category.CategoryService;
import com.gecko.model.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/admin/product")
@PropertySource("classpath:config.properties")
public class ProductController {
    @Autowired
    ServletContext servletContext;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @Value("${path}")
    private String path;

    @GetMapping("")
    public String indexPro(Model model){
        model.addAttribute("products", productService.findAll(1));
        model.addAttribute("totalPage", productService.getTotalPage());
        return "admin/product/product";
    }

    @GetMapping("/{noPage}")
    public String product(@PathVariable("noPage") int noPage, Model model){
        model.addAttribute("products", productService.findAll(noPage));
        model.addAttribute("totalPage", productService.getTotalPage());
        return "admin/product/product";
    }

    @RequestMapping("/add")
    public String add(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "admin/product/add";
    }
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        Product product = productService.findById(id);
        if (product != null){
            model.addAttribute("product", productService.findById(id));
            model.addAttribute("categories", categoryService.getAllCategory());
            return "admin/product/edit";
        }
        return "redirect:/admin/product";
    }

    @RequestMapping("/create")
    public String create(@ModelAttribute("product") Product product,@RequestParam("img") MultipartFile file ,@RequestParam("catalog") int catId){
        String fileName = file.getOriginalFilename();
        File destination = new File(path + fileName);
        try {
            product.setImageUrl(fileName);
            product.setCategory(categoryService.findById(catId));
            if (productService.create(product)){
                file.transferTo(destination);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/admin/product";
    }
    @RequestMapping("/update")
    public String update(@ModelAttribute("product") Product product,@RequestParam("img") MultipartFile file ,@RequestParam("catalog") int catId){
        String fileName = file.getOriginalFilename();
        if (!fileName.isEmpty()){
            File destination = new File(path + fileName);
            try {
                product.setImageUrl(fileName);
                product.setCategory(categoryService.findById(catId));
                if (productService.update(product)){
                    file.transferTo(destination);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return "redirect:/admin/product";
        }else{
            System.out.println("2");
            Product oldPro = productService.findById(product.getProductId());
            product.setImageUrl(oldPro.getImageUrl());
            product.setCategory(categoryService.findById(catId));
            productService.update(product);
            return "redirect:/admin/product";
        }
    }
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        productService.delete(id);
        return "redirect:/admin/product";
    }

}