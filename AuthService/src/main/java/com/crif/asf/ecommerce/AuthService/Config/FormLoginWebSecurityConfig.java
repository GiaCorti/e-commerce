package com.crif.asf.ecommerce.AuthService.Config;

import com.crif.asf.ecommerce.AuthService.Authentication.BasicAuthorizationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@Order(1)
public class FormLoginWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private BasicAuthorizationProvider basicAuthorizationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/auth/**").httpBasic()
                .and().
                csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/auth/**").authenticated()
                .and()
                .authenticationProvider(basicAuthorizationProvider);
    }
}

