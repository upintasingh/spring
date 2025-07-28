package org.example.springproject.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
   private String name;
   private String description;

   @ManyToOne(cascade = CascadeType.ALL)
   private Category category;

   private Double price;

   private String imageUrl;

   private Boolean isPrimeSpecific;

   public Product() {
      this.setCreatedAt(new Date());
      this.setUpdatedAt(new Date());
      this.setState(State.ACTIVE);
   }

}
