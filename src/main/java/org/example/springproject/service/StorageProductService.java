package org.example.springproject.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.springproject.models.Product;
import org.example.springproject.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service("sps")
@Primary
public class StorageProductService implements IProductService{
    @Autowired
    ProductRepo productRepo;

    @Override
    public List<Product> getAllProductDetails() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepo.findById(id);
        if(product.isPresent()) {
            return product.get();
        }
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Product> getProduct = productRepo.findById(product.getId());
        return getProduct.orElseGet(() -> productRepo.save(product));
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }
}
