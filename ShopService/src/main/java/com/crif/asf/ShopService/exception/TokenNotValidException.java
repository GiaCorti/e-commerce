package com.crif.asf.ShopService.exception;

public class TokenNotValidException extends RuntimeException {
    public TokenNotValidException() {
	super("Token not valid");
    }

}
