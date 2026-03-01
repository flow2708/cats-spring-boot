package com.example.demo;

import com.example.demo.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// TODO реализовать html отображение
@Controller
@RequestMapping("/")
public class CatModelController {
    @GetMapping
    public String loginOrRegister(HttpServletRequest request, Model model) {
        // Проверяем куки безопасно
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    String username = cookie.getValue();
                    if (username != null && !username.isEmpty()) {
                        // Если нашли куку - пользователь уже залогинен
                        model.addAttribute("username", username);
                        return "profile";  // Отправляем на профиль
                    }
                }
            }
        }

        return "login";
    }
    @PostMapping
}
