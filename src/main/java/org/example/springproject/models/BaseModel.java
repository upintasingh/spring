package org.example.springproject.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseModel {
    private Long id;
    private Date createdAt;
    private Date updatedAt;

    public BaseModel() {
        setCreatedAt(new Date());
        setUpdatedAt(new Date());
    }
}
