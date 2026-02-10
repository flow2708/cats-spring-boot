package com.example.demo.exceptions;

public class CatNotFoundException extends RuntimeException {
    public CatNotFoundException(Long id) {
        super("Сouldn't find the cat with ID number " + id);
    }
}
