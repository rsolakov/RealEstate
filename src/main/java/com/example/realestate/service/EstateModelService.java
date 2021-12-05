package com.example.realestate.service;

import com.example.realestate.model.view.EstateModelView;

import java.util.List;

public interface EstateModelService {

    void initializeEstateModels();

    List<EstateModelView> getAllModels();
}
