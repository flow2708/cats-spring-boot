package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// TODO реализовать html отображение
@Controller
@RequestMapping("/")
public class CatModelController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String loginOrRegister(HttpServletRequest request, Model model) {]

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    String username = cookie.getValue();
                    if (username != null && !username.isEmpty()) {
                        model.addAttribute("username", username);
                        return "profile";
                    }
                }
            }
        }

        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpServletResponse response,
                        Model model) {

        // Здесь проверка логина (в реальности - через БД)
        if ("user".equals(username) && "pass".equals(password)) {
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(24 * 60 * 60); // 24 часа
            cookie.setPath("/");
            response.addCookie(cookie);

            return "redirect:/profile";
        } else {
            model.addAttribute("error", "Неверный логин или пароль");
            return "login";
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password,
                           Model model) {
        userRepository.save(new User(username, email, password));
        model.addAttribute("message", "Регистрация успешна! Теперь войдите.");
        return "login";
    }

    @GetMapping("/profile")
    public String profile(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    model.addAttribute("username", cookie.getValue());
                    return "profile";
                }
            }
        }

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("username", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/";
    }
}
