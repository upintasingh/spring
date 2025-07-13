package org.example.springproject.clients;

import lombok.RequiredArgsConstructor;
import org.example.springproject.dto.FakeStoreProductDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class FakeStoreApiClient {

    private final RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductDTO getProductById(Long productId) {
        ResponseEntity<FakeStoreProductDTO> response = requestForEntity("https://fakestoreapi.com/products/{productId}", HttpMethod.GET, null, FakeStoreProductDTO.class, productId);
        if(validateResponse(response)) return response.getBody();
        return null;
    }

    private <T> ResponseEntity<T> requestForEntity(String url, HttpMethod method, Object requestBody, Class<T> response, Object... uriVariables) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(requestBody, response);
        ResponseExtractor<ResponseEntity<T>> responseEntityExtractor = restTemplate.responseEntityExtractor(response);
        return restTemplate.execute(url, method, requestCallback, responseEntityExtractor, uriVariables);
    }

    private Boolean validateResponse(ResponseEntity<FakeStoreProductDTO> response) {
        return response.getStatusCode().is2xxSuccessful() && response.hasBody();
    }
}
