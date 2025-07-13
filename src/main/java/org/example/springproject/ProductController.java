package org.example.springproject;


import lombok.RequiredArgsConstructor;
import org.example.springproject.dto.ProductDTO;
import org.example.springproject.models.Category;
import org.example.springproject.models.Product;
import org.example.springproject.service.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    /*@GetMapping("/products")
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId(1);
        product.setName("prod 1");
        products.add(product);
        return products;
    }

    @GetMapping("products/{id}")
    public Product getProductById(@PathVariable int id) {
        Product product = new Product();
        product.setId(id);
        product.setName("product 2");
        return product;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return product;
    }
*/
    private final IProductService productService;
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProductList() {
        List<Product> response =  productService.getAllProductDetails();
        if(response == null || response.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(fromList(response), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable (name = "id")Long productId) {
        if(productId <= 0){
            throw new IllegalArgumentException("Product id must be greater than 0");
        }
        Product response = productService.getProductById(productId);
        if(response == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(from(response), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        Product product = fromProductDToToProduct(productDTO);
        Product response = productService.createProduct(product);
        if(response== null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(from(response));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id,
                                                    @RequestBody ProductDTO productDTO) {
        Product product = fromProductDToToProduct(productDTO);
        Product productResponse = productService.updateProduct(id, product);
        if(productResponse == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(from(productResponse));
    }

    private ProductDTO from(Product product) {
        return ProductDTO.builder().id(product.getId()).description(product.getDescription())
                .title(product.getName()).price(product.getPrice())
                .categoryDTO(product.getCategory().getCategoryName()).build();

    }

    private List<ProductDTO> fromList(List<Product> products) {
        return products.stream().map(this::from).toList();
    }

    private Product fromProductDToToProduct(ProductDTO productDTO) {
        Product product =  new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        Category category = new Category();
        category.setCategoryName(productDTO.getCategoryDTO());
        product.setCategory(category);
        return product;
    }

}
