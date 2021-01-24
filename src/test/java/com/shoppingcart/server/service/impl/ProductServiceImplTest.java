package com.shoppingcart.server.service.impl;

import com.shoppingcart.server.ShoppingCartServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ShoppingCartServerApplication.class,ProductServiceImpl.class})
public class ProductServiceImplTest {

    @Autowired
    ProductServiceImpl productService;

    @Test
    public void testWhenFindAllNames_thenProductNameListShouldBeReturned() throws Exception{
        List<String> productNames = productService.getAllProductNames();
        assertNotNull(productNames);
        assertEquals(2,productNames.size());
    }
}
