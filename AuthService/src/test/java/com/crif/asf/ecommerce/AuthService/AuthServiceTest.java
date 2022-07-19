package com.crif.asf.ecommerce.AuthService;

import com.crif.asf.ecommerce.AuthService.Model.BearerToken;
import com.crif.asf.ecommerce.AuthService.Repository.AuthRepository;
import com.crif.asf.ecommerce.AuthService.Service.AccountService;
import com.crif.asf.ecommerce.AuthService.Service.AuthService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AuthService.class})
public class AuthServiceTest {


    @Autowired
    private AuthService authService;

    @MockBean
    private AuthRepository authRepository;

    @MockBean
    private AccountService accountService;

    /*
    a=0  Token already exist, just return
    a=1 token does not exist, create token and return it
     */
    @ParameterizedTest
    @MethodSource("insertTestArguments")
    public void getTokenTest(int a) {
        String token = UUID.randomUUID().toString();
        String id = UUID.randomUUID().toString();
        String email = UUID.randomUUID().toString();
        if(a==0){
            when(this.accountService.getId(anyString())).thenReturn(id);
            when(this.authRepository.existsById(anyString())).thenReturn(true);
            when(this.authRepository.findById(anyString())).thenReturn(Optional.of(new BearerToken() {{
                setIdUser(id);
                setToken(token);
            }}));
            String ret = this.authService.getToken(email);
            assertNotNull(ret);
            assertEquals(ret,token);
        }
        if(a==1){
            when(this.accountService.getId(anyString())).thenReturn(id);
            when(this.authRepository.existsById(anyString())).thenReturn(false);
            when(this.authRepository.insert((BearerToken) any())).thenReturn(new BearerToken());
            String ret = this.authService.getToken(email);
            assertNotNull(ret);
        }

    }
    public static List<Object[]> insertTestArguments() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{0});
        list.add(new Object[]{1});
        return list;
    }
}
