package com.example.springboot.services;

import com.example.springboot.model.LoginForm;
import com.example.springboot.model.RegisterForm;
import com.example.springboot.repository.LoginRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

    public String login(LoginForm loginForm, Model model) {

        String email = loginForm.getEmail();
        String password = loginForm.getPassword();

        // tutaj potrzebna bardziej zaawansowana autoryzacja
        if ("admin@gmail.com".equals(email) && "password123#".equals(password)){
            model.addAttribute("message", "You logged in successfully");
            model.addAttribute("email", email);
            loginRepository.getUser(email, password);
            return "home";
        } else {
            model.addAttribute("message", "Invalid credidentials");
            return "auth/login";
        }
    }

    public String register(RegisterForm registerForm, Model model) {

        String email = registerForm.getEmail();
        String firstName = registerForm.getFirstName();
        String lastName = registerForm.getLastName();
        String password = registerForm.getPassword();
        String password2 = registerForm.getPassword2();

        if ("user@gmail.com".equals(email) && password.equals(password2)){
            model.addAttribute("message", "You registered succesfully");
            model.addAttribute("email", email);
            return "home";
        } else {
            model.addAttribute("invalidCredentials", true);
            return "auth/register";
        }
    }
}
