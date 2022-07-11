package com.crif.asf.ecommerce.AuthService.Service;

<<<<<<< HEAD
import com.crif.asf.ecommerce.AuthService.model.BearerToken;
import com.crif.asf.ecommerce.AuthService.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

=======
import com.crif.asf.ecommerce.AuthService.Model.BearerToken;
import com.crif.asf.ecommerce.AuthService.Repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
>>>>>>> feature/Auth_first_implementation
@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;

<<<<<<< HEAD

    public String getToken(String username) {
        if(authRepository.existsById(username)){
            return authRepository.findById(username).orElseThrow().getToken();
        }
        else{
            BearerToken bearerToken = new BearerToken(username,UUID.randomUUID().toString());
=======
    @Autowired
    private AccountService accountService;

    public String getToken(String email) {
        String id = this.accountService.getId(email);
        if(authRepository.existsById(id)){
            return authRepository.findById(id).orElseThrow().getToken();
        }
        else{
            BearerToken bearerToken = new BearerToken(id,UUID.randomUUID().toString());
>>>>>>> feature/Auth_first_implementation
            authRepository.insert(bearerToken);
            return bearerToken.getToken();
        }
    }
<<<<<<< HEAD

    public boolean checkToken(BearerToken bearerToken) {
        BearerToken stored = this.authRepository.findById(bearerToken.getIdUser()).orElseThrow();
        if(bearerToken.getToken().equals(stored.getToken())){
            return true;
        }
        return false;
    }
}
=======
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
>>>>>>> feature/Auth_first_implementation
