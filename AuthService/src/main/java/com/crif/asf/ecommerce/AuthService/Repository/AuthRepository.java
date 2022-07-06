package com.crif.asf.ecommerce.AuthService.Repository;

import com.crif.asf.ecommerce.AuthService.Model.BearerToken;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface AuthRepository extends MongoRepository<BearerToken, String> {

}