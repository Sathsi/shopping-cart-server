package com.shoppingcart.server.service.impl;

import com.shoppingcart.server.persistence.repository.ProductRepository;
import com.shoppingcart.server.service.ProductService;
import com.shoppingcart.server.persistence.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<String> getAllProductNames() throws Exception {
        LOGGER.info("Get all product Names service invoked");

        final List<String> resultList = new ArrayList<>();

        final Iterable<com.shoppingcart.server.persistence.dto.Product> dbProductList =
                productRepository.findAll();

        for(Product dbProduct: dbProductList) {
            resultList.add(dbProduct.getProductName());
        }
        return resultList;
    }
}
