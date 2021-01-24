package com.shoppingcart.server.service.impl;

import com.shoppingcart.server.ShoppingCartServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ShoppingCartServerApplication.class,PriceServiceImpl.class})
public class PriceServiceImplTest {

    @Autowired
    PriceServiceImpl priceService;

    @Test
    public void testPriceCalculatorWhenPurchaseTypeIsCartonAndProductNameIsPenguinEars() throws Exception{

        final double totalPriceWithCarton = priceService.getTotalPrice("carton", "Penguin-ears","5");
        assertThat(totalPriceWithCarton).isEqualTo(787.50);

        final double totalPriceForThreeCartons = priceService.getTotalPrice("carton","Penguin-ears","3");
        assertThat(totalPriceForThreeCartons).isEqualTo(472.50);

        final double totalPriceForLesserThanThreeCartons = priceService.getTotalPrice("carton","Penguin-ears","2");
        assertThat(totalPriceForLesserThanThreeCartons).isEqualTo(350.00);

    }

    @Test
    public void testPriceCalculatorWhenPurchaseTypeIsUnitAndProductNameIsPenguinEars() throws Exception{

        final double totalPriceWithUnits = priceService.getTotalPrice("unit","Penguin-ears", "5");
        assertThat(totalPriceWithUnits).isEqualTo(56.88);

        final double totalPriceWhenUnitsMoreThanCartonSize = priceService.getTotalPrice( "unit", "Penguin-ears","25");
        assertThat(totalPriceWhenUnitsMoreThanCartonSize).isEqualTo(231.88);

        final double totalPriceWhenUnitsMoreThanThreeCartonSize = priceService.getTotalPrice("unit", "Penguin-ears","65");
        assertThat(totalPriceWhenUnitsMoreThanThreeCartonSize).isEqualTo(523.69);

        final double totalPriceWhenUnitsSameThreeCartonSize = priceService.getTotalPrice("unit", "Penguin-ears","60");
        assertThat(totalPriceWhenUnitsSameThreeCartonSize).isEqualTo(472.50);

    }

    @Test
    public void testPriceCalculatorWhenPurchaseTypeIsCartonAndProductNameIsHorseshoe() throws Exception{

        final double totalPriceWithCarton = priceService.getTotalPrice("carton", "Horseshoe","5");
        assertThat(totalPriceWithCarton).isEqualTo(3712.50);

        final double totalPriceForThreeCartons = priceService.getTotalPrice("carton","Horseshoe","3");
        assertThat(totalPriceForThreeCartons).isEqualTo(2227.50);

        final double totalPriceForLesserThanThreeCartons = priceService.getTotalPrice("carton","Horseshoe","2");
        assertThat(totalPriceForLesserThanThreeCartons).isEqualTo(1650.00);

    }

    @Test
    public void testPriceCalculatorWhenPurchaseTypeIsUnitAndProductNameIsHorseshoe() throws Exception{

        final double totalPriceWithUnits = priceService.getTotalPrice("unit","Horseshoe", "5");
        assertThat(totalPriceWithUnits).isEqualTo(825.00);

        final double totalPriceWhenUnitsMoreThanCartonSize = priceService.getTotalPrice( "unit", "Horseshoe","25");
        assertThat(totalPriceWhenUnitsMoreThanCartonSize).isEqualTo(3712.50);

        final double totalPriceWhenUnitsMoreThanThreeCartonSize = priceService.getTotalPrice("unit", "Horseshoe","65");
        assertThat(totalPriceWhenUnitsMoreThanThreeCartonSize).isEqualTo(9652.50);

        final double totalPriceWhenUnitsSameThreeCartonSize = priceService.getTotalPrice("unit", "Horseshoe","60");
        assertThat(totalPriceWhenUnitsSameThreeCartonSize).isEqualTo(8910.00);

    }


}
