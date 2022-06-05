package com.example.springboot.services;

import com.example.springboot.model.LoginForm;
import com.example.springboot.model.RegisterForm;
import com.example.springboot.model.User;
import com.example.springboot.repository.LoginRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

    public String login(HttpSession session, LoginForm loginForm, RedirectAttributes redirectAttrs) {

        List<String> messages = new ArrayList<>();
        session.setAttribute("MY_SESSION_MESSAGES", messages);

        String email = loginForm.getEmail();
        String password = loginForm.getPassword();

        User user = loginRepository.findByEmail(email);

        if (user != null){

            byte[] decodedBytes = Base64.getDecoder().decode(user.getPassword());
            String decodedPassword = new String(decodedBytes);

            boolean validPassword = Objects.equals(decodedPassword, password);
            boolean validEmail = user.getEmail().equals(email);

            if (validEmail && validPassword){
                redirectAttrs.addAttribute("email", email);
                redirectAttrs.addFlashAttribute("key", "key");
                return "redirect:/generate?email={email}";
            }
        } else {
            redirectAttrs.addAttribute("message", "Invalid credidentials");
        }
        return "auth/login";
    }

    public String register(RegisterForm registerForm, Model model) {

        String email = registerForm.getEmail();
        String firstName = registerForm.getFirstName();
        String lastName = registerForm.getLastName();
        String password = registerForm.getPassword();
        String password2 = registerForm.getPassword2();

        boolean validPassword = password.equals(password2) && password.length() > 7;
        boolean validEmail = EmailValidator.getInstance().isValid(email);
        boolean validName = firstName.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$")
                && lastName.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$");

        User available = loginRepository.findByEmail(email);

        if (available != null){
            return "auth/register";
        }

        if (validEmail && validName && validPassword){
            model.addAttribute("message", "You registered succesfully");
            model.addAttribute("email", email);

            String passwordEncoded = Base64.getEncoder().encodeToString(password.getBytes());

            User newUser = new User(email, firstName, lastName, passwordEncoded);
            loginRepository.save(newUser);
            return "auth/login";
        } else {
            model.addAttribute("invalidCredentials", true);
            return "auth/register";
        }
    }

    public String logout(HttpServletRequest request, Model model) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}
