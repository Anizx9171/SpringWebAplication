package com.gecko.controller;

import com.gecko.model.entity.CartItem;
import com.gecko.model.entity.Product;
import com.gecko.model.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ProductService productService;
    @Autowired
    HttpSession httpSession;

    @GetMapping("")
    public String cart(Model model){
        if (httpSession.getAttribute("customerLogin") == null){
            return "redirect:/login";
        }
        List<CartItem> cartItems = httpSession.getAttribute("cartItems") != null ? (List<CartItem>) httpSession.getAttribute("cartItems") : new ArrayList<>();
        double total = 0;
        for (CartItem cartItem:cartItems) {
            total += cartItem.getQuantity()*cartItem.getProduct().getPrice();
        }
        model.addAttribute("total", total);
        return "user/cart";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("qty") int qty,@RequestParam("productId") int productId){
        Product product = productService.findById(productId);
        List<CartItem> cartItems = httpSession.getAttribute("cartItems") != null ? (List<CartItem>) httpSession.getAttribute("cartItems") : new ArrayList<>();
        if (product != null){
            boolean check = false;
            for (CartItem cartItem : cartItems) {
                if (cartItem.getProduct().getProductId() == productId && (cartItem.getQuantity() + qty) <= cartItem.getProduct().getQuantity()) {
                    cartItem.setQuantity(cartItem.getQuantity() + qty);
                    check = true;
                    break;
                }
            }
            if (!check){
                CartItem cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setQuantity(qty);
                cartItems.add(cartItem);
            }
            httpSession.setAttribute("cartItems", cartItems);
        }
        return "redirect:/cart";
    }

    @GetMapping("/change-quantity/{value}/{id}")
    public String changeQuantity(@PathVariable("value") int value, @PathVariable("id") int productId){
        List<CartItem> cartItems = httpSession.getAttribute("cartItems") != null ? (List<CartItem>) httpSession.getAttribute("cartItems") : new ArrayList<>();
        if (value == 1){
            for (CartItem cartItem : cartItems) {
                if (cartItem.getProduct().getProductId() == productId && cartItem.getQuantity() > 1) {
                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                    break;
                }
            }
        }else {
            for (CartItem cartItem : cartItems) {
                if (cartItem.getProduct().getProductId() == productId && cartItem.getQuantity() < cartItem.getProduct().getQuantity()) {
                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                    break;
                }
            }
        }
        httpSession.setAttribute("cartItems", cartItems);
        return "redirect:/cart";
    }
    @GetMapping("/remove/{id}")
    public String removeCartItem(@PathVariable("id") int productId) {
        List<CartItem> cartItems = httpSession.getAttribute("cartItems") != null ? (List<CartItem>) httpSession.getAttribute("cartItems") : new ArrayList<>();
            for (CartItem cartItem : cartItems) {
                if (cartItem.getProduct().getProductId() == productId ) {
                    cartItems.remove(cartItem);
                    break;
                }
            }
        return "redirect:/cart";
    }
}
