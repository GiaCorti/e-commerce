package com.crif.asf.ShopService.exception;

public class NotEnoughBalanceException extends RuntimeException {

    public NotEnoughBalanceException() {
	super("You don't have enough money to proceed with the payment");
    }

}
