package com.shoppingcart.server.controller;

import com.shoppingcart.server.models.Product;

import com.shoppingcart.server.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/product")
@Api(tags = "Products")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @ApiOperation(value = "Get all Products", notes = "Will retrieve a list of all the available products")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Successfully retrieve the gateway list", response = Product.class),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "The gateway list you were trying to reach is not found"),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "You are not authorized to view the gateway list"),
            @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Accessing the gateway list you were trying to reach is forbidden"),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server error occurred while retrieving the gateway list")
    })
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getAllProducts() throws Exception {
        LOGGER.info("Get All Product API invoked");
        final List<String> productNameList= productService.getAllProductNames();
        return new ResponseEntity<Object>(productNameList, HttpStatus.OK);
    }

}
