package com.crif.asf.ecommerce.AuthService.Authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class BasicAuth extends UsernamePasswordAuthenticationToken {

    public BasicAuth(String username, String password) {
<<<<<<< HEAD
        super( username, password );

    }
}
=======
        super(username, password);

    }
}
>>>>>>> feature/Auth_first_implementation
