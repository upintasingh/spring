package org.example.springproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryDTO {
     Long id;
     String name;
     String description;
}
