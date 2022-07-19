package com.crif.asf.ShopService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crif.asf.ShopService.model.Cart;
import com.crif.asf.ShopService.model.CartOrder;
import com.crif.asf.ShopService.model.Product;
import com.crif.asf.ShopService.repository.CartRepository;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @InjectMocks
    private CartService cartService;
    @Mock
    private CatalogService catalogService;
    @Mock
    private CartRepository cartRepository;

    @Test
    void getCartTest() {
	String user = "user1";
	Product p1 = new Product(1, "", "", 20.5);
	Product p2 = new Product(2, "", "", 10.0);

	Cart c1 = new Cart(user, 2, p1);
	Cart c2 = new Cart(user, 1, p2);

	List<Cart> cartItemList = List.of(c1, c2);

	Mockito.when(cartRepository.findByIdUserAndCompletedIsFalse(user))
		.thenReturn(cartItemList);

	List<Cart> res = cartService.getCart(user);
	assertEquals(res, cartItemList);
    }

    @Test
    void addProductToCartTest() {
	String user = "user1";
	Integer idProduct = 1;
	CartOrder cartOrder = new CartOrder(idProduct, 1);
	Mockito.when(catalogService.existsById(idProduct)).thenReturn(true);
	cartService.addProductToCart(cartOrder, user);
	verify(cartRepository, times(1)).save(any());
    }

    @Test
    void removeProductFromCart() {
	Integer idProduct = 1;
	String user = "user1";
	Mockito.when(cartRepository.existsByIdProductAndIdUser(idProduct, user))
		.thenReturn(true);
	cartService.removeProductFromCart(idProduct, user);
	verify(cartRepository, times(1)).deleteByIdProductAndIdUser(any(), any());

    }

}
