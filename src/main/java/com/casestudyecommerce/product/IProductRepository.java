package com.casestudyecommerce.product;

import com.casestudyecommerce.brand.Brand;
import com.casestudyecommerce.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByNameContaining(String name, Pageable pageable);

    Page<Product> findAllByBrand(Brand brand, Pageable pageable);

    Page<Product> findAllByCategory(Category category, Pageable pageable);
}
