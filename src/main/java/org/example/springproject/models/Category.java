package org.example.springproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String categoryName;
    private String categoryDescription;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Product> products;

    public Category() {
        this.setCreatedAt(new Date());
        this.setUpdatedAt(new Date());
        this.setState(State.ACTIVE);
        this.products = new ArrayList<>();
    }


}
