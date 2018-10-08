package com.example.postgretest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


//@EnableAuthorizationServer
//@EnableResourceServer
@SpringBootApplication

public class PostgretestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostgretestApplication.class, args);
    }
}
