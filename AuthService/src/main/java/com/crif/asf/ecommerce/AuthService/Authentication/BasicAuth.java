package com.crif.asf.ecommerce.AuthService.Authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class BasicAuth extends UsernamePasswordAuthenticationToken {

    public BasicAuth(String username, String password) {

        super( username, password );

    }
}
