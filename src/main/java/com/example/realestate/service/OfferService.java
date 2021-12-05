package com.example.realestate.service;

import com.example.realestate.model.binding.OfferAddBindingModel;
import com.example.realestate.model.service.OfferAddServiceModel;
import com.example.realestate.model.service.OfferUpdateServiceModel;
import com.example.realestate.model.view.DetailsView;
import com.example.realestate.model.view.OfferView;
import javassist.tools.rmi.ObjectNotFoundException;

import java.util.List;

public interface OfferService {
    void initializeOffers();

    List<OfferView> getAllOffers();

    DetailsView findById(Long id, String currentUser);

    OfferAddServiceModel addOffer(OfferAddBindingModel offerAddBindingModel, String ownerId);

    void deleteOffer(Long id);

    boolean isOwner(String userName, Long id);

    void updateOffer(OfferUpdateServiceModel serviceModel) throws ObjectNotFoundException;

}

