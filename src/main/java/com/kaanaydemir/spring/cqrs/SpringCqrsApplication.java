package com.kaanaydemir.spring.cqrs;

import com.kaanaydemir.spring.cqrs.command.api.exception.ProductServiceEventsErrorHandler;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCqrsApplication {

    public static void main(String[] args) {
        SpringApplication.run (SpringCqrsApplication.class, args);
    }

    @Autowired
    public void configure(EventProcessingConfigurer configurer) {
        configurer.registerListenerInvocationErrorHandler (
                "product",
                configuration -> new ProductServiceEventsErrorHandler ()
        );
    }

}
