package com.casestudyecommerce.restController.image;


import com.casestudyecommerce.image.IImageService;
import com.casestudyecommerce.image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private IImageService imageService;

    @GetMapping
    public ResponseEntity<Page<Image>> findAll(Pageable pageable) {
        Page<Image> imagePage = imageService.findAll(pageable);
        if (imagePage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(imagePage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> findById(@PathVariable Long id, @RequestBody Image image) {
        Optional<Image> imageOptional = imageService.findById(id);
        if (!imageOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(imageOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Image> create(@RequestBody Image image) {
        return new ResponseEntity<>(imageService.save(image), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Image> update(@PathVariable Long id, @RequestBody Image image) {
        Optional<Image> imageOptional = imageService.findById(id);
        if (!imageOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        imageService.save(image);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Image> delete(@PathVariable Long id) {
        Optional<Image> imageOptional = imageService.findById(id);
        if (!imageOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        imageService.deleteById(id);
        return new ResponseEntity<>(imageOptional.get(), HttpStatus.OK);
    }
}
