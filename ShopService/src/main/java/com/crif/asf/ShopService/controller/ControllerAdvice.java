package com.crif.asf.ShopService.controller;

import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.crif.asf.ShopService.exception.NotAnAdminException;
import com.crif.asf.ShopService.exception.NotEnoughBalanceException;
import com.crif.asf.ShopService.exception.ProductAlreadyExistsException;
import com.crif.asf.ShopService.exception.ProductNotFoundException;
import com.crif.asf.ShopService.exception.ProductNotInCartException;
import com.crif.asf.ShopService.exception.TokenNotValidException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(SQLTransientConnectionException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "DB service not available")
    void SqlTransientHandle(SQLTransientConnectionException exc) {
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Element not present")
    void NoSuchElementHandle(NoSuchElementException exc) {
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Element not present")
    void ProductNotFoundExceptionHandle(ProductNotFoundException exc) {
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Element is already present")
    void ProductAlreadyExistsExceptionHandle(ProductAlreadyExistsException exc) {
    }

    @ExceptionHandler(NotEnoughBalanceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Not enough balance")
    void NotEnoughBalanceExceptionHandle(NotEnoughBalanceException exc) {
    }

    @ExceptionHandler(ProductNotInCartException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Product not in cart")
    void ProductNotInCartExceptionHandle(ProductNotInCartException exc) {
    }

    @ExceptionHandler(TokenNotValidException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Token not valid")
    void TokenNotValidExceptionHandle(TokenNotValidException exc) {
    }

    @ExceptionHandler(NotAnAdminException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Not an admin")
    void NotAnAdminExceptionHandle(NotAnAdminException exc) {
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Issues with your operation")
    void SQLHandle(SQLException exc) {
    }

}
