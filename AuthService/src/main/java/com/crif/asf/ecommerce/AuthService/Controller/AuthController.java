package com.crif.asf.ecommerce.AuthService.Controller;

import com.crif.asf.ecommerce.AuthService.Service.AuthService;
import com.crif.asf.ecommerce.AuthService.model.Account;
import com.crif.asf.ecommerce.AuthService.model.BearerToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("login")
    public String add(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();
       return this.authService.getToken(username);
    }


}
