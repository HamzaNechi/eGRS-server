package com.orange.orangegrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class OrangeGrsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrangeGrsApplication.class, args);
    }

}
