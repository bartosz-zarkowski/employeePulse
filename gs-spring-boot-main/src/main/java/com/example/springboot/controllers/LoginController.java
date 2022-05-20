package com.example.springboot.controllers;

import com.example.springboot.model.LoginForm;
import com.example.springboot.model.RegisterForm;
import com.example.springboot.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String login(HttpSession session, @ModelAttribute (name="loginForm")LoginForm loginForm, RedirectAttributes redirectAttrs){
        return loginService.login(session, loginForm, redirectAttrs);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterForm(){
        return "auth/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute (name="registerForm")RegisterForm registerForm, Model model){
        return loginService.register(registerForm, model);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, Model model){
        return loginService.logout(request, model);
    }

}