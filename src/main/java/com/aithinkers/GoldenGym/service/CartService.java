package com.aithinkers.GoldenGym.service;



import java.util.List;

import com.aithinkers.GoldenGym.entity.CartItem;



public interface CartService {
	List<CartItem> getCartItems();
	 void addToCart(Long id, int quantity);
	 void updateCartItem(Long Id, int quantity);
	 void removeFromCart(Long Id);
	 double calculateTotal();
	 void checkout();
	
}