package com.example.realestate.service.impl;

import com.example.realestate.model.entity.EstateModel;
import com.example.realestate.model.entity.enums.CategoryEstateEnum;
import com.example.realestate.model.view.EstateModelView;
import com.example.realestate.repository.EstateModelRepository;
import com.example.realestate.service.EstateModelService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstateModelServiceImpl implements EstateModelService {

    private final EstateModelRepository estateModelRepository;
    private final ModelMapper modelMapper;

    public EstateModelServiceImpl(EstateModelRepository estateModelRepository, ModelMapper modelMapper) {
        this.estateModelRepository = estateModelRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void initializeEstateModels() {
        if (estateModelRepository.count() == 0) {

            EstateModel apartment = new EstateModel();
            apartment.setName("Apartment");
            apartment.setCategory(CategoryEstateEnum.APARTMENT);
            apartment.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/1/13/2_Bedroom_Apartments_in_Orlando.jpg");

            EstateModel house = new EstateModel();
            house.setName("House");
            house.setCategory(CategoryEstateEnum.HOUSE);
            house.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/f/f7/Casa_pe_structura_din_lemn.jpg");

            EstateModel office = new EstateModel();
            office.setName("Office");
            office.setCategory(CategoryEstateEnum.OFFICE);
            office.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b7/Kinnarpsmobler.jpg/180px-Kinnarpsmobler.jpg");


            EstateModel garage = new EstateModel();
            garage.setName("Garage");
            garage.setCategory(CategoryEstateEnum.GARAGE);
            garage.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/8/8f/133_Ashley_-_garage.jpg/1280px-133_Ashley_-_garage.jpg");

            estateModelRepository.saveAll(List.of(apartment, house, office, garage));
        }
    }

    @Override
    public List<EstateModelView> getAllModels() {
        return estateModelRepository.findAll()
                .stream()
                .map(estateModel -> {
                    EstateModelView estateModelView = new EstateModelView();
                    estateModelView
                            .setName(estateModel.getName())
                            .setCategory(estateModel.getCategory())
                            .setId(estateModel.getId())
                            .setImageUrl(estateModel.getImageUrl());

                    return modelMapper.map(estateModelView, EstateModelView.class);
                }).collect(Collectors.toList());
    }


}
