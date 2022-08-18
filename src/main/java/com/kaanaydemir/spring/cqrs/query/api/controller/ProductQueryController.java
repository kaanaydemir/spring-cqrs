package com.kaanaydemir.spring.cqrs.query.api.controller;


import com.kaanaydemir.spring.cqrs.command.api.model.ProductRestModel;
import com.kaanaydemir.spring.cqrs.query.api.queries.GetProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductQueryController {

    private final QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ProductRestModel> getAllProducts() {
        GetProductsQuery getProductsQuery = new GetProductsQuery ();

        List<ProductRestModel> productRestModels = queryGateway
                .query (getProductsQuery, ResponseTypes.multipleInstancesOf (ProductRestModel.class))
                .join ();

        return productRestModels;
    }
}
