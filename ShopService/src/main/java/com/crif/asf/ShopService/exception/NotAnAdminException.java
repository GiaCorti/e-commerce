package com.crif.asf.ShopService.exception;

public class NotAnAdminException extends RuntimeException {

    public NotAnAdminException() {
	super("You must be admin to perform this action");
    }

}
