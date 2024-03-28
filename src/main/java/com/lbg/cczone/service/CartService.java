package com.lbg.cczone.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lbg.cczone.Repos.CartRepo;
import com.lbg.cczone.domain.Cart;

@Service
public class CartService {

	private CartRepo repo;

	public CartService(CartRepo repo) {
		super();
		this.repo = repo;
	}

//	public List<Cart> getCart() {
//		return this.repo.findAll();
//	}
	// No longer use this method
//	public List<Cart> getCart() {
//		List<Cart> cartTotal = this.repo.findAll();
//		List<Item> items;
//		for (Cart cart : cartTotal) {
//			items = cart.getItems();
//			Double total = 0.0;
//			for (Item item : items) {
//				total = total + (item.getItemPrice()) * (item.getItemQuantity());
//			}
//			System.out.println("cart" + cart.getId() + ": " + total);
//		}
//
//		return this.repo.findAll();
//	}

	public ResponseEntity<Cart> getCart(int id) {
		Optional<Cart> found = this.repo.findById(id);
//		if (found.isEmpty()) {
//			return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
//		}
		Cart body = found.get();
		return ResponseEntity.ok(body);

	}
//Method not in use

//	public ResponseEntity<Object> createCart(Cart cart) {
//
//		Cart created = this.repo.save(cart);
//		return new ResponseEntity<Object>(created, HttpStatus.CREATED);
//	}
//
//	public ResponseEntity<Cart> updateCart(int id, Cart cart) {
//		Optional<Cart> found = this.repo.findById(id);
//		if (found.isEmpty()) {
//			return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
//		}
//		Cart existing = found.get();
//		if (cart.getItems() != null) {
//			existing.setItems(cart.getItems());
//		}
//		if (cart.getBuyer() != null) {
//			existing.setBuyer(cart.getBuyer());
//		}
//
//		Cart updated = this.repo.save(existing);
//		return ResponseEntity.ok(updated);
//
//	}
	// No longer use this method
//	public boolean deleteCart(@PathVariable int id) {
//		this.repo.deleteById(id);
//		return !this.repo.existsById(id);
//	}

}
