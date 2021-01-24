package com.shoppingcart.server.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActualProductPrice {
    private String productName;
    private int numberOfUnits;
    private double price;
}
