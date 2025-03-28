package com.foursales.desafio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.foursales.desafio.infra.elasticsearch.repositories")
public class DesafioApplication {
    public static void main(String[] args) {
        SpringApplication.run(DesafioApplication.class, args);
    }

}