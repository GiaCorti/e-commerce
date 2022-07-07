package com.crif.asf.ecommerce.AuthService.Controller;

import com.crif.asf.ecommerce.AuthService.Service.AuthService;
import com.crif.asf.ecommerce.AuthService.Model.BearerToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal/")
public class InternalController {
    @Autowired
    private AuthService authService;

    @GetMapping("getUser")
    public String checkToken(@RequestParam String token) {
        return this.authService.checkToken(token);
    }
}