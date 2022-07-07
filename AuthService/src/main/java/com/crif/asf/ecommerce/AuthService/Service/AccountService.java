package com.crif.asf.ecommerce.AuthService.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountService {

    RestTemplate restTemplate = new RestTemplate();
    String url = "localhost:14002";
    public String getPass(String username) {

        ResponseEntity<String> response = restTemplate.getForEntity(url + "/1", String.class);


        if (username.equals("email")) return "pass";
        return "nopass";
    }
}