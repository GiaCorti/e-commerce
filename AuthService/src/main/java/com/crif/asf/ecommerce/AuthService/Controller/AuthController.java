package com.crif.asf.ecommerce.AuthService.Controller;

import com.crif.asf.ecommerce.AuthService.Service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("login")
    public String login(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();
       return this.authService.getToken(username);
    }

    @GetMapping("isAdmin")
    public boolean isAdmin(@RequestParam String token){ return this.authService.isAdmin(token);}

    @GetMapping("getUser")
    public String getUserFromToken(@RequestParam String token) {
        return this.authService.getUserFromToken(token);
    }

}

