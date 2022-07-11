package com.crif.asf.ecommerce.AuthService.Controller;

import com.crif.asf.ecommerce.AuthService.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal/")
public class InternalController {
    @Autowired
    private AuthService authService;

    @GetMapping("getUser")
    public String getUserFromToken(@RequestParam String token) {
        return this.authService.getUserFromToken(token);
    }

    @GetMapping("checkToken")
    public boolean isTokenValid(@RequestParam String token){
        return this.authService.isTokenValid(token);
    }

    @GetMapping("checkAdmin")
    public boolean isAdmin(@RequestParam String token){ return this.authService.isAdmin(token);}
}