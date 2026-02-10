package com.example.demo.exceptions;

/**
 *     Без обработчика: Spring вернет статус 500 (Internal Server Error) с техническим стектрейсом.
 *     С этим обработчиком: Вернется статус 404 (NOT_FOUND) с понятным сообщением: "Кот с id 5 не найден".
 */
public class CatNotFoundException extends RuntimeException {
    public CatNotFoundException(Long id) {
        super("Сouldn't find the cat with ID number " + id);
    }
}
