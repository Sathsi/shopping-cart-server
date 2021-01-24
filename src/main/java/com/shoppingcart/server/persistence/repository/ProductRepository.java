package com.shoppingcart.server.persistence.repository;

import com.shoppingcart.server.persistence.dto.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findProductByproductId(int productId);

    Product findProductByproductName(String productName);

}
