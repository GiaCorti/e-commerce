package com.crif.asf.ecommerce.AuthService.Config;

<<<<<<< HEAD
=======

>>>>>>> feature/Auth_first_implementation
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import java.util.Collection;
import java.util.Collections;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "AuthDB";
    }

    @Override
    public MongoClient mongoClient() {
<<<<<<< HEAD
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:15000/AuthDB");
=======
        ConnectionString connectionString = new ConnectionString("mongodb://host.docker.internal:15000/AuthDB");
>>>>>>> feature/Auth_first_implementation
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Override
    public Collection getMappingBasePackages() {
        return Collections.singleton("com.crif.asf");
    }
}