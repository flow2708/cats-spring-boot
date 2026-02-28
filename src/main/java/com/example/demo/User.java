package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Random;

@Entity
public class User {
    @Id
    private Long id;
    private String name;
    private int age;

    public User(String name, int age) {
        id = new Random().nextLong();
        this.name = name;
        this.age = age;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setAge(int id) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
