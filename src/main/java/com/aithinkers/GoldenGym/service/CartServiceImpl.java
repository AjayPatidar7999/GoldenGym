package com.aithinkers.GoldenGym.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aithinkers.GoldenGym.entity.CartItem;
import com.aithinkers.GoldenGym.entity.Supplement;
import com.aithinkers.GoldenGym.repo.CartRepository;
import com.aithinkers.GoldenGym.repo.SupplementRepository;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    

    @Autowired
    private SupplementRepository supplementRepository;

    // Get all cart items
    public List<CartItem> getCartItems() {
        return cartRepository.findAll();
    }

    // Add item to cart
    
    public void addToCart(Long id, int quantity) {
        // Fetch the supplement by ID
        Supplement supplement = supplementRepository.findById(id).orElse(null);

        if (supplement != null) {
            // Check if the supplement is already in the cart
            CartItem cartItem = cartRepository.findBySupplementId(id);

            if (cartItem != null) {
                // If item exists, update the quantity
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
            } else {
                // If item doesn't exist, create a new cart item
                cartItem = new CartItem(supplement, quantity);
            }

            // Save the cart item to the database
            cartRepository.save(cartItem);
        }
    }

    // Update cart item quantity
    public void updateCartItem(Long Id, int quantity) {
        cartRepository.findById(Id).ifPresent(cartItem -> {
            cartItem.setQuantity(quantity);
            cartRepository.save(cartItem);
        });
    }

    // Remove item from cart
    public void removeFromCart(Long Id) {
        cartRepository.deleteById(Id);
    }

    // Calculate total price
    public double calculateTotal() {
        return cartRepository.findAll().stream()
                .mapToDouble(item -> item.getSupplement().getPrice() * item.getQuantity())
                .sum();
    }

    // Checkout (Clears cart after payment)
    public void checkout() {
        cartRepository.deleteAll();
    }
}
