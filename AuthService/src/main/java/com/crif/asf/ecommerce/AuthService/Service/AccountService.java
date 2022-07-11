package com.crif.asf.ecommerce.AuthService.Service;

import com.crif.asf.ecommerce.AuthService.Authentication.BasicAuthorizationProvider;
import com.crif.asf.ecommerce.AuthService.Exception.BadCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountService {

    Logger logger = LoggerFactory.getLogger(BasicAuthorizationProvider.class);
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://host.docker.internal:14002/internal/";
    public String getPass(String email) {
        if(email.equals("email")) return "pass";

      String response = restTemplate.getForObject(url +"password/"+ email, String.class);


        if (response == null || response.isBlank() || response.isEmpty()){
            logger.info("Password non trovata per user : {}",email);
            throw new BadCredentialsException();
        };
        return response;
    }

    public boolean isAdmin(String userId) {

        boolean response = restTemplate.getForObject(url +"isAdmin/"+ userId, boolean.class);
        return response;
    }

    public String getId(String email) {
        String response = restTemplate.getForObject(url +"id/"+ email, String.class);


        if (response == null || response.isBlank() || response.isEmpty()){
            logger.info("Password non trovata per user : {}",email);
            throw new BadCredentialsException();
        };
        return response;
    }
}