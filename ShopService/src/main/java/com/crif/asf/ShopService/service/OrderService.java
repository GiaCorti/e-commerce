package com.crif.asf.ShopService.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crif.asf.ShopService.exception.NotEnoughBalanceException;
import com.crif.asf.ShopService.model.Cart;
import com.crif.asf.ShopService.model.Order;
import com.crif.asf.ShopService.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserHelperService userHelperService;

    @Transactional
    public Order buy(String idUser) {

	// get current cart of idUser
	List<Cart> cartsToProcess = cartService.getCart(idUser);

	// compute total
	Double tot = cartsToProcess
		.stream()
		.map(c -> c.getProduct().getPrice() * c.getQty())
		.reduce(0.0, Double::sum);

	// check if balance is enough
	if (userHelperService.getBalance(idUser) < tot)
	    throw new NotEnoughBalanceException();

	// set all not completed carts to completed
	cartService.setCompletedCart(idUser);

	// save in orders table
	final String idOrder = UUID.randomUUID().toString();
	cartsToProcess
		.stream()
		.forEach(c -> orderRepository.save(
			new Order(idOrder, c, LocalDateTime.now(), tot)));

	// TODO return a Receipt Object
	return null;
    }
}
