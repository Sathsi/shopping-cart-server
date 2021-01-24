package com.shoppingcart.server.service.impl;

import com.shoppingcart.server.models.ActualProductPrice;
import com.shoppingcart.server.persistence.dto.Carton;
import com.shoppingcart.server.persistence.dto.Product;
import com.shoppingcart.server.persistence.repository.CartonRepository;
import com.shoppingcart.server.persistence.repository.ProductRepository;
import com.shoppingcart.server.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PriceServiceImpl.class);

    @Autowired
    CartonRepository cartonRepository;

    @Autowired
    ProductRepository productRepository;

    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @Override
    public List<ActualProductPrice> getActualPriceList() throws Exception {
        LOGGER.info("Get actual product price service invoked");

        final List<ActualProductPrice> resultList = new ArrayList<>();
        final Iterable<Carton> cartonList = cartonRepository.findAll();

        for(int i=1; i<=50; i++) {
            for(Carton carton : cartonList) {
                resultList.add(actualProductPriceCalculator(i,carton.getCartonPrice(),carton.getProductId(),
                        carton.getUnitsPerCarton()));
            }

        }

        return resultList;
    }

    @Override
    public double getTotalPrice(final String purchaseType, final String productName, final String numberOfItems) throws Exception{
        LOGGER.info("Request for calculate product prices service invoked");
        double cartonPrice = 0.00;
        if(purchaseType != null && productName != null && numberOfItems != null ){
            int numberOfItms = Integer.parseInt(numberOfItems);
            if(purchaseType.toLowerCase().equals("carton")) {
                cartonPrice = Double.parseDouble(decimalFormat.format(getTotalPriceForCarton(productName,numberOfItms)));
            }else{
                cartonPrice = Double.parseDouble(decimalFormat.format(getTotalPriceForSingleUnits(productName,numberOfItms)));
            }
        }
        return Double.parseDouble(decimalFormat.format(cartonPrice));
    }

    private ActualProductPrice actualProductPriceCalculator(final int numberOfUnits, final double cartonPrice,
                                                            final int productId, final int unitsPerCarton ){

        final Product dbProduct = productRepository.findProductByproductId(productId);
        final double singleUnitPrice = (cartonPrice / unitsPerCarton) * (1.3);
        double actualPrice;

        if(numberOfUnits % unitsPerCarton == 0){
            if((numberOfUnits / unitsPerCarton) >= 3) {
                actualPrice = (numberOfUnits / unitsPerCarton) * cartonPrice * (0.9);
            }else {
                actualPrice = (numberOfUnits / unitsPerCarton) * cartonPrice;
            }
        }else {
            final int numberOfSingleUnits = numberOfUnits % unitsPerCarton;
            final int numberOfCartons = (int) numberOfUnits / unitsPerCarton;
            final double totalSingleUnitsPrice = numberOfSingleUnits * singleUnitPrice;
            final double totalCartonsPrice = numberOfCartons * cartonPrice;
            actualPrice = totalSingleUnitsPrice + totalCartonsPrice;

            if(numberOfCartons >= 3){
                actualPrice = actualPrice * (0.9);
            }
        }
        actualPrice = Double.parseDouble(decimalFormat.format(actualPrice));
        ActualProductPrice actualProductPrice = new ActualProductPrice();
        actualProductPrice.setProductName(dbProduct.getProductName());
        actualProductPrice.setNumberOfUnits(numberOfUnits);
        actualProductPrice.setPrice(actualPrice);
        return actualProductPrice;

    }

    private double getTotalPriceForCarton(final String productName, final int numberOfItems){
        Product dbProduct = productRepository.findProductByproductName(productName);
        Carton dbCarton = cartonRepository.findProductByproductId(dbProduct.getProductId());
        double cartonPrice;

        if(numberOfItems >= 3){
            cartonPrice = numberOfItems * dbCarton.getCartonPrice() * 0.9;
        }else {
            cartonPrice = numberOfItems *  dbCarton.getCartonPrice();
        }
        return cartonPrice;
    }

    private double getTotalPriceForSingleUnits(final String productName, final int numberOfItems){
        Product dbProduct = productRepository.findProductByproductName(productName);
        Carton dbCarton = cartonRepository.findProductByproductId(dbProduct.getProductId());
        ActualProductPrice actualProductPrice = actualProductPriceCalculator(numberOfItems,
                dbCarton.getCartonPrice(),dbProduct.getProductId(),dbCarton.getUnitsPerCarton() );
        return actualProductPrice.getPrice();
    }
}
