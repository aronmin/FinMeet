package com.finmeet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FinMeetApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinMeetApplication.class, args);
    }

}
