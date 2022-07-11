package com.crif.asf.ShopService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String ACCOUNT_SERVICE_URL = "http://host.docker.internal:14002/internal";
    // private static final String ACCOUNT_SERVICE_URL =
    // "http://localhost:14002/internal";
    private static final String GET_BALANCE = "/getBalance";
    private static final String SUBTRACT_BALANCE = "/subtractBalance";

    // Rest Template request to AccountService (id nel path, nome:balance)
    public Double getBalance(String idUser) {
	// return 1000000.0;

	return restTemplate.getForObject(
		String.format("%s%s/?user=%s",
			ACCOUNT_SERVICE_URL, GET_BALANCE, idUser),
		Double.class);
    }

    public Double subtractBalance(String idUser, Double total) {
	return restTemplate.patchForObject(
		String.format("%s%s/?user=%s&total=%s",
			ACCOUNT_SERVICE_URL, SUBTRACT_BALANCE, idUser, total),
		null, Double.class);
    }
}
