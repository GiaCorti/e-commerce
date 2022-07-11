package com.crif.asf.ecommerce.AuthService.Service;

import com.crif.asf.ecommerce.AuthService.Model.BearerToken;
import com.crif.asf.ecommerce.AuthService.Repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private AccountService accountService;

    public String getToken(String email) {
        String id = this.accountService.getId(email);
        if(authRepository.existsById(id)){
            return authRepository.findById(id).orElseThrow().getToken();
        }
        else{
            BearerToken bearerToken = new BearerToken(id,UUID.randomUUID().toString());
            authRepository.insert(bearerToken);
            return bearerToken.getToken();
        }
    }
    public String getUserFromToken(String token) {
        BearerToken t = this.authRepository.findByToken(token);
        if(t==null) return null;
    return t.getIdUser();
    }

    public boolean isTokenValid(String token) {
        return this.authRepository.existsByToken(token);
    }

    public boolean isAdmin(String token) {
        String userId = this.getUserFromToken(token);
        return this.accountService.isAdmin(userId);
    }
}