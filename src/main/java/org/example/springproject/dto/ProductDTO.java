package org.example.springproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDTO {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String imageURL;
    private CategoryDTO categoryDTO;
}

