package com.crif.asf.ecommerce.AuthService.Controller;

import com.crif.asf.ecommerce.AuthService.Service.AuthService;
import com.crif.asf.ecommerce.AuthService.Model.BearerToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/internal/")
public class InternalController {
    @Autowired
    private AuthService authService;
    @GetMapping("checkToken")
    public boolean checkToken(@RequestBody BearerToken bearerToken){
        return this.authService.checkToken(bearerToken);
    }
}