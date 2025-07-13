package org.example.springproject.service;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.example.springproject.clients.FakeStoreApiClient;
import org.example.springproject.dto.FakeStoreProductDTO;
import org.example.springproject.models.Category;
import org.example.springproject.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FakeStoreProductService implements IProductService{

    private final RestTemplateBuilder restTemplateBuilder;

    private final FakeStoreApiClient fakeStoreApiClient;

    @Override
    public List<Product> getAllProductDetails() {
        RestTemplate restTemplate = restTemplateBuilder.build();
         ResponseEntity<FakeStoreProductDTO[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);
         if(response.getStatusCode().is2xxSuccessful() && response.getBody()!=null) {
             return fromList(List.of(response.getBody()));
         }
        return null;
    }

    @Override
    public Product getProductById(Long id) {
       /* RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDTO.class, id);
        if(response.getStatusCode().equals(HttpStatus.OK) && response.hasBody()) {
            return from(Objects.requireNonNull(response.getBody(), "Response body must not be null"));
        }
        return null;*/
        FakeStoreProductDTO response = fakeStoreApiClient.getProductById(id);
        if(response==null) {
            return null;
        }
        return from(response);
    }

    

    @Override
    public Product createProduct(Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDTO inputFakeStoreProductDTO = fromProductToFake(product);
        ResponseEntity<FakeStoreProductDTO>  response  = restTemplate.postForEntity("https://fakestoreapi.com/products", inputFakeStoreProductDTO, FakeStoreProductDTO.class);
        if(response.getStatusCode().equals(HttpStatus.OK) && response.hasBody()) {
            return from(Objects.requireNonNull(response.getBody(), "Response body must not be null"));
        }
        return null;

    }

    @Override
    public Product updateProduct(Long id,Product product) {
        FakeStoreProductDTO inputFakeStoreProductDTO = fromProductToFake(product);
        ResponseEntity<FakeStoreProductDTO> responseEntity = putForEntity("https://fakestoreapi.com/products/{Id}", inputFakeStoreProductDTO, FakeStoreProductDTO.class, id);
            if(responseEntity.getStatusCode().equals(HttpStatus.OK) && responseEntity.hasBody()) {
                return from(Objects.requireNonNull(responseEntity.getBody(), "Response body must not be null"));
            }
        return null;
    }

    public <T> ResponseEntity<T> putForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor =  restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }

    private Product from(FakeStoreProductDTO fakeStoreProductDTO) {
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setName(fakeStoreProductDTO.getTitle());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setPrice(fakeStoreProductDTO.getPrice());
        Category category = new Category();
        category.setCategoryName(fakeStoreProductDTO.getCategory());
        product.setCategory(category);
        return product;
    }

    private List<Product> fromList(List<FakeStoreProductDTO> fakeStoreProductDTO) {
        if(fakeStoreProductDTO == null || fakeStoreProductDTO.isEmpty()) {
            return Collections.emptyList();
        }

        return fakeStoreProductDTO.stream().map(this::from).toList();
    }

    private FakeStoreProductDTO fromProductToFake(Product product) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId(product.getId());
        fakeStoreProductDTO.setTitle(product.getName());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setCategory(product.getCategory().getCategoryName());
        return fakeStoreProductDTO;
    }
}
