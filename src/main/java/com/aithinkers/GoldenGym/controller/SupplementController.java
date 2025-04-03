package com.aithinkers.GoldenGym.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aithinkers.GoldenGym.entity.CartItem;
import com.aithinkers.GoldenGym.entity.Supplement;
import com.aithinkers.GoldenGym.service.CartService;
import com.aithinkers.GoldenGym.service.SupplementService;



@Controller
@RequestMapping("/supplements")
public class SupplementController {
    @Autowired
    private SupplementService supplementService;
    @Autowired
    private CartService cartService;

    @GetMapping("/slist")
    public String viewSupplements(@RequestParam(required = false) String category, Model model) {
        List<Supplement> supplements = (category != null) ?
                supplementService.getSupplementsByCategory(category) : supplementService.getAllSupplements();
        model.addAttribute("supplements", supplements);
        return "Supplement/supplement";
    } 

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam Long id, @RequestParam int quantity) {
        cartService.addToCart(id, quantity);
        return "redirect:/supplements/cart";
        
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        List<CartItem> cart = cartService.getCartItems();
        double total = cart.stream().mapToDouble(item -> item.getSupplement().getPrice() * item.getQuantity()).sum();
        model.addAttribute("cart", cart);
        model.addAttribute("total", total);
        return "Supplement/cart";
    }

    @PostMapping("/remove-from-cart")
    public String removeFromCart(@RequestParam Long id) {
        cartService.removeFromCart(id);
        return "redirect:/supplements/cart";
    }


   
 

        // Update Quantity
        @PostMapping("/update-cart")
        public String updateCart(@RequestParam Long id, @RequestParam int quantity) {
            cartService.updateCartItem(id, quantity);
            return "redirect:/supplements/slist";
        }

      

        // Proceed to Checkout
        @PostMapping("/checkout")
        public String checkout() {
            cartService.checkout();
            return "Supplement/payment";
        }
        
        @PostMapping("/apply-discount")
        public String applyDiscount() {
        	return "Supplement/discount";
        	
        }
    }




