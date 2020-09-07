package org.sang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling//开启定时任务支持
public class CustomMiYouSheApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomMiYouSheApplication.class, args);
    }
}
