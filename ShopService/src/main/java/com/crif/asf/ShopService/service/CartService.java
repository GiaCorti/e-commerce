package com.crif.asf.ShopService.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crif.asf.ShopService.exception.ProductNotFoundException;
import com.crif.asf.ShopService.exception.ProductNotInCartException;
import com.crif.asf.ShopService.model.Cart;
import com.crif.asf.ShopService.model.CartOrder;
import com.crif.asf.ShopService.model.Product;
import com.crif.asf.ShopService.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CatalogService catalogService;

    public List<Cart> getCart(String idUser) {
	return cartRepository.findByIdUserAndCompletedIsFalse(idUser);
    }

    public void addProductToCart(CartOrder cartOrder, String idUser) {
	if (!catalogService.existsById(cartOrder.getIdProduct()))
	    throw new ProductNotFoundException();
	Product p = catalogService.getProductById(cartOrder.getIdProduct());
	cartRepository.save(new Cart(idUser, cartOrder.getQty(), p));
    }

    @Transactional
    public void removeProductFromCart(Integer idProduct, String idUser) {
	if (!cartRepository.existsByIdProductAndIdUser(idProduct, idUser))
	    throw new ProductNotInCartException();

	cartRepository.deleteByIdProductAndIdUser(idProduct, idUser);
    }

    public void setCompletedCart(String idUser) {
	List<Cart> currentCarts = this.getCart(idUser);
	currentCarts.forEach(c -> c.setCompleted(true));
	cartRepository.saveAll(currentCarts);
    }

}
