package com.example.realestate.model.entity;

import com.example.realestate.model.entity.enums.CategoryEstateEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "estate_model")
public class EstateModel extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryEstateEnum category;
    @Column()
    private String imageUrl;



    public EstateModel() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String estateName) {
        this.name = estateName;
    }

    public CategoryEstateEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEstateEnum category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
