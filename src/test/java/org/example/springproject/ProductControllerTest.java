package org.example.springproject;

import org.example.springproject.dto.ProductDTO;
import org.example.springproject.models.Product;
import org.example.springproject.service.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
public class ProductControllerTest {

    @MockitoBean
    private IProductService productService;

    @Autowired
    ProductController productController;


    @Test
    public void testCreateProduct_withValidInput_runSuccess() {
        //arrange
        ProductDTO productDTO = ProductDTO.builder().id(1L).title("title").build();
        Product product = new Product();
        product.setId(1L);
        product.setName("title");
        when(productService.createProduct(product)).thenReturn(product);


        //act
        ResponseEntity<ProductDTO> response = productController.createProduct(productDTO);
        assertEquals("checking product id", productDTO, response.getBody());

    }
}
