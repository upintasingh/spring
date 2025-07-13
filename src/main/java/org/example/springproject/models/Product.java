package org.example.springproject.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Product extends BaseModel{
   private String name;
   private String description;
   private Category category;
   private Double price;

}
