package com.crif.asf.ecommerce.AuthService.Service;

import com.crif.asf.ecommerce.AuthService.model.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountService {



    public String getPass(String username) {
        if(username.equals("email")) return "pass";
        return "nopass";
    }
}
