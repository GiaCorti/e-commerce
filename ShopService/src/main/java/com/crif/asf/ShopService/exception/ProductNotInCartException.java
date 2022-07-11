package com.crif.asf.ShopService.exception;

public class ProductNotInCartException extends RuntimeException {

    public ProductNotInCartException() {
	super("This product is not in your cart");
    }

}
