package org.example.springproject.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Category extends BaseModel {
    private String categoryName;
    private String categoryDescription;
    private List<Product> products;


}
