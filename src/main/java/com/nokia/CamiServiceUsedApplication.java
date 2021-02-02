package com.nokia;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CamiServiceUsedApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CamiServiceUsedApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
//        SpringApplication.run(CamiServiceUsedApplication.class,args);
    }

}
