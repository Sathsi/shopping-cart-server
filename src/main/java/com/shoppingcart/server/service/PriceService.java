package com.shoppingcart.server.service;

import com.shoppingcart.server.models.ActualProductPrice;

import java.util.List;

public interface PriceService {

     List<ActualProductPrice> getActualPriceList() throws Exception;

     double getTotalPrice(String purchaseType, String productName, String numberOfItems);
}
