package com.crif.asf.ecommerce.AuthService.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("BearerToken")
public class BearerToken {
    @Id
    private String idUser;
    private String token;

    public BearerToken(String idUser, String token) {
        this.idUser = idUser;
        this.token = token;
    }

    public BearerToken() {
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}