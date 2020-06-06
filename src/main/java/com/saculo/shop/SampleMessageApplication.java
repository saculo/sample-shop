package com.saculo.shop;

import com.fasterxml.jackson.databind.Module;
import io.vavr.jackson.datatype.VavrModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class SampleMessageApplication {

    public static void main (String[] args) {
        SpringApplication.run(SampleMessageApplication.class, args);
    }

    @Bean
    Module vavrModule() {
        return new VavrModule();
    }

}
