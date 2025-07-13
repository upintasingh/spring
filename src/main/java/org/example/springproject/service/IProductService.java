package org.example.springproject.service;

import org.example.springproject.models.Product;

import java.util.List;

public interface IProductService {
    public List<Product> getAllProductDetails();

    public Product getProductById(Long id);

    public Product createProduct(Product product);

    public Product updateProduct(Long id,Product product);
}
