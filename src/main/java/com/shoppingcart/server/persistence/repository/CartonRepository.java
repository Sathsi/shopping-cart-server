package com.shoppingcart.server.persistence.repository;

import com.shoppingcart.server.persistence.dto.Carton;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartonRepository extends CrudRepository<Carton, Long> {

    Carton findProductByproductId(int productId);

}
