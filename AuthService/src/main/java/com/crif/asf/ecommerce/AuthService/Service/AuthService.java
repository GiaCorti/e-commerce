package com.crif.asf.ecommerce.AuthService.Service;

import com.crif.asf.ecommerce.AuthService.Exception.AccountNotExistException;
import com.crif.asf.ecommerce.AuthService.Model.BearerToken;
import com.crif.asf.ecommerce.AuthService.Repository.AuthRepository;
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
    public String checkToken(String token) {
        BearerToken t = this.authRepository.findByToken(token);
        if(t==null) throw new AccountNotExistException();
    return t.getIdUser();
    }
}