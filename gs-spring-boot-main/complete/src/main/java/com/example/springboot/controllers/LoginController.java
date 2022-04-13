package com.example.springboot.controllers;

import com.example.springboot.model.LoginForm;
import com.example.springboot.model.RegisterForm;
import com.example.springboot.services.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getLoginFrom() {
        return "auth/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute (name="loginForm")LoginForm loginForm, Model model){
        return loginService.login(loginForm, model);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterForm(){
        return "auth/register";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute (name="registerForm")RegisterForm registerForm, Model model){
        return loginService.register(registerForm, model);
    }

}