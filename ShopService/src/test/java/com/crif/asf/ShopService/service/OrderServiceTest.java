package com.crif.asf.ShopService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crif.asf.ShopService.DTO.OrderDTO;
import com.crif.asf.ShopService.assembler.OrderAssembler;
import com.crif.asf.ShopService.exception.NotEnoughBalanceException;
import com.crif.asf.ShopService.model.Cart;
import com.crif.asf.ShopService.model.Order;
import com.crif.asf.ShopService.model.Product;
import com.crif.asf.ShopService.repository.CartRepository;
import com.crif.asf.ShopService.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;
    @Mock
    private CartService cartService;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private AccountService accountService;

    @Test
    void buyOkBalanceTest() {

	String user = "user1";
	Product p1 = new Product(1, "", "", 20.5);
	Product p2 = new Product(2, "", "", 10.0);

	// total: 51
	Cart c1 = new Cart(user, 2, p1);
	Cart c2 = new Cart(user, 1, p2);
	// mock carts to process
	List<Cart> cartItemList = List.of(c1, c2);
	Mockito.when(cartService.getCart(user))
		.thenReturn(cartItemList);

	// mock check balance from user account
	Mockito.when(accountService.getBalance(user)).thenReturn(70.0);

	// mock orders
	List<Order> orders = List.of(
		new Order("1", c1, LocalDateTime.now(), 41.0),
		new Order("1", c2, LocalDateTime.now(), 10.0));

	List<OrderDTO> ordersDTO = orders
		.stream()
		.map(o -> OrderAssembler.assemble(o))
		.collect(Collectors.toList());

	Mockito.when(orderRepository.saveAll(any())).thenReturn(orders);

	List<OrderDTO> res = orderService.buy(user);

	assertEquals(ordersDTO, res);

    }

    @Test
    void buyNoBalanceTest() {

	String user = "user1";
	Product p1 = new Product(1, "", "", 20.5);
	Product p2 = new Product(2, "", "", 10.0);

	// total: 51
	Cart c1 = new Cart(user, 2, p1);
	Cart c2 = new Cart(user, 1, p2);
	// mock carts to process
	List<Cart> cartItemList = List.of(c1, c2);
	Mockito.when(cartService.getCart(user))
		.thenReturn(cartItemList);

	// mock check balance from user account
	Mockito.when(accountService.getBalance(user)).thenReturn(50.0);

	assertThrows(NotEnoughBalanceException.class,
		() -> orderService.buy(user));

    }

}
