package com.example.realestate.model.service;

import com.example.realestate.model.entity.enums.CategoryEstateEnum;

public class EstateModelServiceModel {

    private Long id;
    private String name;
    private CategoryEstateEnum category;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
