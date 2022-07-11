package com.crif.asf.ShopService.exception;

public class ProductAlreadyExistsException extends RuntimeException {

    public ProductAlreadyExistsException() {
	super("This product already exists");
    }

}
