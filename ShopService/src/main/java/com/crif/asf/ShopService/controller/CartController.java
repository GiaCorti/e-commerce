package com.crif.asf.ShopService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crif.asf.ShopService.DTO.OrderDTO;
import com.crif.asf.ShopService.exception.TokenNotValidException;
import com.crif.asf.ShopService.model.Cart;
import com.crif.asf.ShopService.model.CartOrder;
import com.crif.asf.ShopService.service.AuthService;
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
    @Autowired
    private AuthService authService;

    // hard-coded user
    // String idUser = "cfed9bb6-44f0-4b4a-be36-0688551f6ea8";

    @GetMapping
    public List<Cart> getCart(@RequestHeader("Authorization") String token) {
	String idUser = authService.getIdUser(token);
	if (idUser == null)
	    throw new TokenNotValidException();
	return cartService.getCart(idUser);
    }

    @PostMapping
    public void addProductToCart(@RequestBody CartOrder cartOrder,
	    @RequestHeader("Authorization") String token) {
	String idUser = authService.getIdUser(token);
	if (idUser == null)
	    throw new TokenNotValidException();
	cartService.addProductToCart(cartOrder, idUser);
    }

    @DeleteMapping
    public void removeProductFromCart(
	    @RequestParam(name = "id_product", required = true) Integer idProduct,
	    @RequestHeader("Authorization") String token) {
	String idUser = authService.getIdUser(token);
	if (idUser == null)
	    throw new TokenNotValidException();
	cartService.removeProductFromCart(idProduct, idUser);
    }

    @DeleteMapping("/empty")
    public void removeAllProductFromCart(

	    @RequestHeader("Authorization") String token) {
	String idUser = authService.getIdUser(token);
	if (idUser == null)
	    throw new TokenNotValidException();
	cartService.removeAllProductFromCart(idUser);
    }

    @PostMapping("/buy")
    public List<OrderDTO> buy(@RequestHeader("Authorization") String token) {
	String idUser = authService.getIdUser(token);
	if (idUser == null)
	    throw new TokenNotValidException();
	return orderService.buy(idUser);
    }

}
