package com.example.demo.model;

import com.example.demo.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @NotBlank(message = "Имя обязательно")
    @Size(min = 5, max = 20, message = "Имя может содержать от 5 до 20 символов")
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Email(message = "Некорректный email")
    @NotBlank(message = "Email обязателен")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$", message = "Минимум 6 символов, цифра, заглавная и строчная буква")
    @Column(name = "password", nullable = false)
    private String password;

    public User() {}

    public User(Role role, String username, String email, String password) {
        this.role = role;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}