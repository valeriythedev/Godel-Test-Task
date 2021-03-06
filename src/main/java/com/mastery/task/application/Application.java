package com.mastery.task.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.mastery.task.**")
@EnableJpaRepositories("com.mastery.task.repository")
@EntityScan("com.mastery.task.model.**")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

