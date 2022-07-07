package com.crif.asf.ShopService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crif.asf.ShopService.model.Cart;
import com.crif.asf.ShopService.model.CartOrder;
import com.crif.asf.ShopService.model.Order;
import com.crif.asf.ShopService.service.CartService;
import com.crif.asf.ShopService.service.OrderService;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    // hard-coded user
    String idUser = "cfed9bb6-44f0-4b4a-be36-0688551f6ea8";

    @GetMapping
    public List<Cart> getCart() {
	// UUID idUser = UUID.fromString(idUser.toUpperCase());
	return cartService.getCart(idUser);
    }

    @PostMapping
    public void addProductToCart(@RequestBody CartOrder cartOrder) {
	cartService.addProductToCart(cartOrder, idUser);
    }

    @DeleteMapping
    public void removeProductFromCart(
	    @RequestParam(name = "id_product", required = true) Integer idProduct) {
	cartService.removeProductFromCart(idProduct, idUser);
    }

    @GetMapping("/buy")
    public Order buy() {
	return orderService.buy(idUser);
    }

}
