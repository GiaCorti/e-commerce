package com.crif.asf.ecommerce.AuthService.Controller;

import com.crif.asf.ecommerce.AuthService.Service.AuthService;
<<<<<<< HEAD
import com.crif.asf.ecommerce.AuthService.model.Account;
import com.crif.asf.ecommerce.AuthService.model.BearerToken;
=======
>>>>>>> feature/Auth_first_implementation
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestBody;
=======
>>>>>>> feature/Auth_first_implementation
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
public class AuthController {
<<<<<<< HEAD

=======
>>>>>>> feature/Auth_first_implementation
    @Autowired
    private AuthService authService;

    @GetMapping("login")
<<<<<<< HEAD
    public String add(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();
       return this.authService.getToken(username);
    }


}
=======
    public String add() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();
        return this.authService.getToken(username);
    }
}
>>>>>>> feature/Auth_first_implementation
