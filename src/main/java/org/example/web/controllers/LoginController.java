package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.web.dto.LoginForm;
import org.example.app.services.LoginService;
import org.example.app.exceptions.BookShelfLoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private final Logger log = Logger.getLogger(LoginController.class);
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String login(Model model) {
        log.info("Get /login return login_page.html");
        model.addAttribute("loginForm", new LoginForm());
        return "login_page";
    }

    @PostMapping("/auth")
    public String authenticate(LoginForm loginForm) throws BookShelfLoginException {
        if (loginService.authentification(loginForm)){
            log.info("Login successful");
            return "redirect:/books/shelf";
        } else {
            log.info("Login failed");
            throw new BookShelfLoginException("invalid username or password");
        }
    }

    //Обработчик ошибок
    @ExceptionHandler(BookShelfLoginException.class)
    public String handleException(Model model, BookShelfLoginException ex) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "errors/404";
    }

}
