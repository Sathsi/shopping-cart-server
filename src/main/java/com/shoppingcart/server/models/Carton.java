package com.shoppingcart.server.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Carton {

    private int cartonId;
    private double cartonPrice;
    private int productId;
    private int unitsPerCarton;

}
