package com.casestudyecommerce.product;

import com.casestudyecommerce.IGeneralService;
import com.casestudyecommerce.brand.Brand;
import com.casestudyecommerce.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService extends IGeneralService<Product> {
    Page<Product> findAllByNameContaining(String name, Pageable pageable);

    Page<Product> findAllByBrand(Brand brand, Pageable pageable);

    Page<Product> findAllByCategory(Category category, Pageable pageable);

}
