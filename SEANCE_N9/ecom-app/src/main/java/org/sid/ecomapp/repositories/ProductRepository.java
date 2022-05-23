package org.sid.ecomapp.repositories;

import org.sid.ecomapp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
