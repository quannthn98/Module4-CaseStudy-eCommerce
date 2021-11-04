package com.casestudyecommerce.restController.product;

import com.casestudyecommerce.brand.Brand;
import com.casestudyecommerce.category.Category;
import com.casestudyecommerce.image.IImageService;
import com.casestudyecommerce.image.Image;
import com.casestudyecommerce.product.IProductService;
import com.casestudyecommerce.product.Product;
import com.casestudyecommerce.product.ProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    private IProductService productService;

    @Autowired
    private IImageService iImageService;

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(@RequestParam(name = "q") Optional<String> q, Pageable pageable) {
        Page<Product> productPage;
        if (q.isPresent()) {
            productPage = productService.findAllByNameContaining(q.get(), pageable);
        } else {
            productPage = productService.findAll(pageable);
        }
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> create(ProductForm productForm) throws IOException {
        MultipartFile multipartFileMainImage = productForm.getMainImage();
        String fileNameMainImage = multipartFileMainImage.getOriginalFilename();
        FileCopyUtils.copy(productForm.getMainImage().getBytes(), new File(fileUpload + fileNameMainImage));
        List<MultipartFile> multipartFileSubImage = productForm.getSubImage();
        List<Image> imageList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFileSubImage) {
            Image image = new Image();
            String fileNameSubImage = multipartFile.getOriginalFilename();
            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + new Date().getTime() + fileNameSubImage));
            image.setName(fileNameSubImage);
            iImageService.save(image);
            imageList.add(image);
        }
        Product product = new Product(productForm.getName(),
                productForm.getQuantity(),
                productForm.getPrice(),
                productForm.getSaleOff(),
                fileNameMainImage,
                imageList,
                productForm.getBrand(),
                productForm.getDescription(),
                productForm.getCategory());
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.deleteById(id);
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }
}
