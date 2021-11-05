package com.casestudyecommerce.image;

import com.casestudyecommerce.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService implements IImageService {
    @Autowired
    private IImageRepository ImageRepository;

    @Override
    public Page<Image> findAll(Pageable pageable) {
        return ImageRepository.findAll(pageable);
    }

    @Override
    public Optional<Image> findById(Long id) {
        return ImageRepository.findById(id);
    }

    @Override
    public Image save(Image image) {
        return ImageRepository.save(image);
    }

    @Override
    public void deleteById(Long id) {
        ImageRepository.deleteById(id);
    }

    @Override
    public Iterable<Image> findAllByProduct(Product product) {
        return ImageRepository.findAllByProduct(product);
    }
}
