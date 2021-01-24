package com.shoppingcart.server.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name="carton")
public class Carton {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int cartonId;
    @NotNull
    private double cartonPrice;
    @NotNull
    private int productId;
    @NotNull
    private int unitsPerCarton;
}
