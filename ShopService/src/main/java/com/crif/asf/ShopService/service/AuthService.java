package com.crif.asf.ShopService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String AUTH_SERVICE_URL = "http://host.docker.internal:14000/internal";
    // private static final String AUTH_SERVICE_URL =
    // "http://localhost:14000/internal";
    private static final String GET_USER = "/getUser";
    private static final String CHECK_TOKEN = "/checkToken";
    private static final String IS_ADMIN = "/checkAdmin";

    // Rest Template request to AuthService
    public boolean isTokenValid(String token) {
	return restTemplate.getForObject(
		String.format("%s%s?token=%s",
			AUTH_SERVICE_URL, CHECK_TOKEN, token.substring(7)),
		Boolean.class);
    }

    public String getIdUser(String token) {
	// can be null
	return restTemplate.getForObject(
		String.format("%s%s?token=%s",
			AUTH_SERVICE_URL, GET_USER, token.substring(7)),
		String.class);
    }

    public boolean isAdmin(String token) {
	return restTemplate.getForObject(
		String.format("%s%s?token=%s",
			AUTH_SERVICE_URL, IS_ADMIN, token.substring(7)),
		Boolean.class);
    }

}
