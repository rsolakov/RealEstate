package com.example.realestate.model.binding;

import com.example.realestate.model.entity.enums.CategoryEstateEnum;

public class GetModelsBindingModel {
    CategoryEstateEnum categoryEstateEnum;

    public CategoryEstateEnum getCategoryEstateEnum() {
        return categoryEstateEnum;
    }

    public void setCategoryEstateEnum(CategoryEstateEnum categoryEstateEnum) {
        this.categoryEstateEnum = categoryEstateEnum;
    }
}
