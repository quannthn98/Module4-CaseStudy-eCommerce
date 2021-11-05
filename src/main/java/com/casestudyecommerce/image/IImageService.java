package com.casestudyecommerce.image;

import com.casestudyecommerce.IGeneralService;
import com.casestudyecommerce.product.Product;

public interface IImageService extends IGeneralService<Image> {
    Iterable<Image> findAllByProduct(Product product);
}
