package com.crif.asf.ShopService.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
	super("There isn't a product with this id");
    }
}
