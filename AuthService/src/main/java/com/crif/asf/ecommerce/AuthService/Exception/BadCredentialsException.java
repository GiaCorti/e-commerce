package com.crif.asf.ecommerce.AuthService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class BadCredentialsException extends RuntimeException {
}
