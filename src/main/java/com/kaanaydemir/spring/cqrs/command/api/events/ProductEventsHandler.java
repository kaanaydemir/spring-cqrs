package com.kaanaydemir.spring.cqrs.command.api.events;


import com.kaanaydemir.spring.cqrs.command.api.data.Product;
import com.kaanaydemir.spring.cqrs.command.api.data.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup ("product")
public class ProductEventsHandler {

    private  final  ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        Product product = new Product ();
        BeanUtils.copyProperties (productCreatedEvent,product);
        productRepository.save (product);
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }
}
