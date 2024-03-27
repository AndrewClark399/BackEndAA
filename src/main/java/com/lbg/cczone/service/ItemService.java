package com.lbg.cczone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lbg.cczone.Repos.CartRepo;
import com.lbg.cczone.Repos.ItemRepo;
import com.lbg.cczone.domain.Cart;
import com.lbg.cczone.domain.Item;
import com.lbg.cczone.dtos.ItemDTO;

@Service
public class ItemService {
	private ItemRepo repo;

	private CartRepo cartRepo;

	public ItemService(ItemRepo repo, CartRepo cartRepo) {
		super();
		this.repo = repo;
		this.cartRepo = cartRepo;
	}

	public List<ItemDTO> getItem() {
		List<Item> items = this.repo.findAll();

		List<ItemDTO> dtos = new ArrayList<>();

		for (Item item : items) {
			ItemDTO dto = new ItemDTO();

			dto.setId(item.getId());
			dto.setItemName(item.getItemName());
			dto.setItemPrice(item.getItemPrice());
			dto.setItemQuantity(item.getItemQuantity());
			dto.setImage(item.getImage());
			if (item.getCart() != null) {
				dto.setCartId(item.getCart().getId());
			}
			dtos.add(dto);

		}
		return dtos;

	}

	public ResponseEntity<Item> getItem(int id) {
		Optional<Item> found = this.repo.findById(id);
		if (found.isEmpty()) {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}
		Item body = found.get();
		return ResponseEntity.ok(body);
	}

	public ResponseEntity<Item> createItem(Item item) {
		Item created = this.repo.save(item);
		return new ResponseEntity<Item>(created, HttpStatus.CREATED);
	}

	public ResponseEntity<Item> updateItem(int id, Item item) {
		Optional<Item> found = this.repo.findById(id);
		if (found.isEmpty()) {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}
		Item existing = found.get();
		if (item.getItemName() != null) {
			existing.setItemName(item.getItemName());
		}
		if (item.getItemPrice() != null) {
			existing.setItemPrice(item.getItemPrice());
		}
		if (item.getItemQuantity() != null) {
			existing.setItemQuantity(item.getItemQuantity());
		}
		if (item.getCart() != null) {
			existing.setCart(item.getCart());
		}

		Item updated = this.repo.save(existing);
		return ResponseEntity.ok(updated);

	}

	public boolean deleteItem(int id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);

	}

	public ResponseEntity<Object> addToCart(Integer itemId, Integer cartId) {
		Optional<Item> itemAdd = this.repo.findById(itemId);

		if (itemAdd.isEmpty()) {
			return new ResponseEntity<Object>("This item doesn't exist", HttpStatus.NOT_FOUND);
		}

		Item existing = itemAdd.get();

		if (existing.getCart() != null) {
			return new ResponseEntity<Object>("This item doesn't exist", HttpStatus.BAD_REQUEST);
		}

		Optional<Cart> cart = this.cartRepo.findById(cartId);

		if (cart.isEmpty()) {
			return new ResponseEntity<Object>("This cart doesn't exist", HttpStatus.BAD_REQUEST);
		}

		existing.setCart(cart.get());

		Item updated = this.repo.save(existing);
		return ResponseEntity.ok(updated);

	}

}
