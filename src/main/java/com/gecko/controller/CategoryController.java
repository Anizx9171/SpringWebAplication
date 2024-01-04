package com.gecko.controller;

import com.gecko.model.entity.Category;
import com.gecko.model.entity.Customer;
import com.gecko.model.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
     @Autowired
    CategoryService categoryService;
     @Autowired
    HttpSession httpSession;

    @GetMapping("/{id}")
    public String category(@PathVariable("id") int id, Model model){
        model.addAttribute("categories", categoryService.findAll(id));
        model.addAttribute("totalPage", categoryService.getTotalPage());
        return "admin/category/category";
    }

    @GetMapping("")
    public String index(){
        return "redirect:/admin/category/1";
    }

    @RequestMapping("/add")
    public String add(Model model){
        Customer customer = (Customer) httpSession.getAttribute("customerLogin");
        if (customer != null && customer.isAdmin()){
            model.addAttribute("category", new Category());
            return "admin/category/add";
        }
        return "redirect:/";
    }
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id,Model model){
        Customer customer = (Customer) httpSession.getAttribute("customerLogin");
        if (customer != null && customer.isAdmin()){
            Category category = categoryService.findById(id);
            if (category != null){
                model.addAttribute("category", category);
                return "admin/category/edit";
            }else {
                return "redirect:/admin/category/1";
            }
        }
        return "redirect:/";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute("category") Category category){
        categoryService.create(category);
        return "redirect:/admin/category/1";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("category") Category category){
        categoryService.update(category);
        return "redirect:/admin/category/1";
    }

    @GetMapping("/delete/{id}")
    public String create(@PathVariable("id") int id){
        categoryService.delete(id);
        return "redirect:/admin/category/1";
    }
}