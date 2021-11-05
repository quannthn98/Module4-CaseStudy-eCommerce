package com.casestudyecommerce.restController.brand;

import com.casestudyecommerce.brand.Brand;
import com.casestudyecommerce.brand.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/brands")
public class BrandController {
    @Autowired
    private IBrandService brandService;

    @GetMapping
    public ResponseEntity<Page<Brand>> findAll(Pageable pageable) {
        Page<Brand> brandPage = brandService.findAll(pageable);
        if (brandPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(brandPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> findById(@PathVariable Long id) {
        Optional<Brand> brandOptional = brandService.findById(id);
        if (!brandOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(brandOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Brand> create(@RequestBody Brand brand) {
        return new ResponseEntity<>(brandService.save(brand), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brand> update(@PathVariable Long id, @RequestBody Brand brand) {
        Optional<Brand> brandOptional = brandService.findById(id);
        if (!brandOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        brandService.save(brand);
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Brand> delete(@PathVariable Long id) {
        Optional<Brand> brandOptional = brandService.findById(id);
        if (!brandOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        brandService.deleteById(id);
        return new ResponseEntity<>(brandOptional.get(), HttpStatus.OK);
    }
}
