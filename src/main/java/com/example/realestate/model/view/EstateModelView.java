package com.example.realestate.model.view;

import com.example.realestate.model.entity.enums.CategoryEstateEnum;

public class EstateModelView {
    private Long id;
    private String name;
    private CategoryEstateEnum category;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public EstateModelView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public EstateModelView setName(String name) {

        this.name = name;
        return this;
    }

    public CategoryEstateEnum getCategory() {
        return category;
    }

    public EstateModelView setCategory(CategoryEstateEnum category) {
        this.category = category;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public EstateModelView setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
