package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.LoginForm;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final Logger logger = Logger.getLogger(LoginService.class);

    public boolean authentification(LoginForm loginForm) {
        logger.info("try auth with username " + loginForm.getUsername());
        return loginForm.getUsername().equals("admin") && loginForm.getPassword().equals("admin");
    }
}
