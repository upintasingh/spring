package org.example.springproject.repos;

import org.example.springproject.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findById(Long aLong);
}
