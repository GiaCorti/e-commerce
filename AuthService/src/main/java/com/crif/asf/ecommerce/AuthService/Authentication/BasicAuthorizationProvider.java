package com.crif.asf.ecommerce.AuthService.Authentication;

import com.crif.asf.ecommerce.AuthService.Exception.AuthenticationException;
import com.crif.asf.ecommerce.AuthService.Service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@ComponentScan("com.crif.asf.md4giacorti.md4giacortiesercitazione1.Service.Service")
public class BasicAuthorizationProvider implements AuthenticationProvider {

    Logger logger = LoggerFactory.getLogger(BasicAuthorizationProvider.class);
    @Autowired
    private AccountService accountService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getPrincipal().toString(); // (1)
        String password = authentication.getCredentials().toString();
        String psw = accountService.getPass(username);
        logger.info("Email: {}  pass: {}", username, password);
        if (psw.equals(password)) {

            return new UsernamePasswordAuthenticationToken(username, password, Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
        } else throw new AuthenticationException();

    }

    @Override
    public boolean supports(Class<?> authentication) {

        if (authentication.isAssignableFrom(BasicAuth.class)) {
            return true;
        }
        return false;
    }
}