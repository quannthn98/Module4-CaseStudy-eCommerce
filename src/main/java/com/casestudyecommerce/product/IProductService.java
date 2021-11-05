package com.casestudyecommerce.product;

import com.casestudyecommerce.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService extends IGeneralService<Product> {
    Page<Product> findAllByNameContaining(String name, Pageable pageable);

    Page<Product> findAllByBrandContaining(String brand, Pageable pageable);
}
