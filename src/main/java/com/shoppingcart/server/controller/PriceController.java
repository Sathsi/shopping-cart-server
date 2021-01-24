package com.shoppingcart.server.controller;


import com.shoppingcart.server.models.ActualProductPrice;
import com.shoppingcart.server.service.PriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/productPrices")
@Api(tags = "ProductPrices")
public class PriceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    PriceService priceService;

    @ApiOperation(value = "Get actual price list", notes = "Will retrieve the actual product prices")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Successfully retrieve the gateway", response = ActualProductPrice.class),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "The gateway you were trying to reach is not found"),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "You are not authorized to view the gateway"),
            @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Accessing the gateway you were trying to reach is forbidden"),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server error occurred while retrieving the gateway")
    })
    @RequestMapping(value = "/priceList", method = RequestMethod.GET)
    public ResponseEntity<Object> getActualProductPrices() throws Exception{
        LOGGER.info("Get actual price list API invoked");
        final List<ActualProductPrice> actualProductPriceList = priceService.getActualPriceList();
        return new ResponseEntity<Object>(actualProductPriceList, HttpStatus.OK);
    }

    @ApiOperation(value = "Get actual price list", notes = "Will retrieve the actual product prices")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Successfully retrieve the gateway", response = ActualProductPrice.class),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "The gateway you were trying to reach is not found"),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "You are not authorized to view the gateway"),
            @ApiResponse(code = HttpURLConnection.HTTP_FORBIDDEN, message = "Accessing the gateway you were trying to reach is forbidden"),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server error occurred while retrieving the gateway")
    })
    @RequestMapping(value = "/calculate/totalPrice", method = RequestMethod.GET)
    public ResponseEntity<Object> getTotalPrice(@RequestParam String purchaseType,
                                                @RequestParam String productName,
                                                @RequestParam String numberOfItems) throws Exception{
        LOGGER.info("Calculate total price API invoked");
        final double calculatedPrice = priceService.getTotalPrice(purchaseType, productName, numberOfItems);
        return new ResponseEntity<Object>(calculatedPrice, HttpStatus.OK);
    }

}
