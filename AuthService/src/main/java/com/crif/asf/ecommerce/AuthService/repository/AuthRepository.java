package com.crif.asf.ecommerce.AuthService.repository;

import com.crif.asf.ecommerce.AuthService.model.BearerToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthRepository extends MongoRepository<BearerToken, String> {

}
