package org.sid.ecomapp.repositories;

import org.sid.ecomapp.entities.Category;
import org.sid.ecomapp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
