package com.casestudyecommerce.category;


import com.casestudyecommerce.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService extends IGeneralService<Category> {
    Page<Category> findAllByNameContaining(String name, Pageable pageable);

    Iterable<Category> findAll();
}
