package com.crif.asf.ecommerce.AuthService.Service;

import com.crif.asf.ecommerce.AuthService.model.BearerToken;
import com.crif.asf.ecommerce.AuthService.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;


    public String getToken(String username) {
        if(authRepository.existsById(username)){
            return authRepository.findById(username).orElseThrow().getToken();
        }
        else{
            BearerToken bearerToken = new BearerToken(username,UUID.randomUUID().toString());
            authRepository.insert(bearerToken);
            return bearerToken.getToken();
        }
    }

    public boolean checkToken(BearerToken bearerToken) {
        BearerToken stored = this.authRepository.findById(bearerToken.getIdUser()).orElseThrow();
        if(bearerToken.getToken().equals(stored.getToken())){
            return true;
        }
        return false;
    }
}
