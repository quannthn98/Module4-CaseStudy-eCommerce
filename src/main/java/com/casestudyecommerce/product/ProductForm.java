package com.casestudyecommerce.product;

import com.casestudyecommerce.brand.Brand;
import com.casestudyecommerce.category.Category;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@Data
public class ProductForm {
    private Long id;
    @NotEmpty
    private String name;

    private int quantity;

    private double price;

    private double saleOff;

    @NotEmpty
    private MultipartFile mainImage;

    private List<MultipartFile> subImage;

    private Brand brand;

    @NotEmpty
    private String description;

    private Category category;
}
