package com.crif.asf.ecommerce.AuthService.Config;

import com.crif.asf.ecommerce.AuthService.Authentication.BasicAuthorizationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(2)
public class InternalWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/internal/**").hasIpAddress("127.0.0.1/32");

    }
}