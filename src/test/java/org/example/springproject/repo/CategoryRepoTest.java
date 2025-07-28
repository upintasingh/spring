package org.example.springproject.repo;


import jakarta.transaction.Transactional;
import org.example.springproject.models.Category;
import org.example.springproject.models.Product;
import org.example.springproject.repos.CategoryRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    @Transactional
    public void findByID() {
       Category category = categoryRepo.findById(1L).get();
        /*for(Product product : category.getProducts()) {
            System.out.println(product.getName());
        }*/
    }
}
