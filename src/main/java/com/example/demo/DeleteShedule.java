package com.example.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
@EnableScheduling
public class DeleteShedule {
    private static final Logger log = LoggerFactory.getLogger(DeleteShedule.class);
    private final CatRepository catRepository;

    public DeleteShedule(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @Scheduled(fixedRate = 5000)
    public void deleteCat() {
        catRepository.deleteAll();
        log.info("All cats are deleted from database!");
    }
}
