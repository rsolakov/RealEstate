package com.example.realestate.service.impl;

import com.example.realestate.model.binding.OfferAddBindingModel;
import com.example.realestate.model.entity.Offer;
import com.example.realestate.model.entity.User;
import com.example.realestate.model.entity.UserRole;
import com.example.realestate.model.entity.enums.CategoryEstateEnum;
import com.example.realestate.model.entity.enums.DealType;
import com.example.realestate.model.entity.enums.RegionEnum;
import com.example.realestate.model.entity.enums.UserRoleEnum;
import com.example.realestate.model.service.OfferAddServiceModel;
import com.example.realestate.model.service.OfferUpdateServiceModel;
import com.example.realestate.model.view.DetailsView;
import com.example.realestate.model.view.OfferView;
import com.example.realestate.repository.EstateModelRepository;
import com.example.realestate.repository.OfferRepository;
import com.example.realestate.repository.UserRepository;
import com.example.realestate.service.OfferService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final EstateModelRepository estateModelRepository;
    private final UserRepository userRepository;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, EstateModelRepository estateModelRepository, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.estateModelRepository = estateModelRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void initializeOffers() {

        if (offerRepository.count() == 0) {
            Offer offer1 = new Offer();
            offer1.setCategoryEstateEnum(CategoryEstateEnum.APARTMENT);
            offer1.setType(DealType.SELL);
            offer1.setName("Apartment with 2 bedrooms and 2 bathrooms");
            offer1.setPrice(new BigDecimal(100000));
            offer1.setRooms(3);
            offer1.setFloor(3);
            offer1.setM2(250.00);
            offer1.setYear(2000);
            offer1.setRegion(RegionEnum.Plovdiv);
            offer1.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b1/Ria_Apartments.jpg/375px-Ria_Apartments.jpg");
            offer1.setTown("Пловдив");
            offer1.setDescription("I don't want description");
            offer1.setSeller(userRepository.findByUsername("rsolakov")
                    .orElse(null));

            Offer offer2 = new Offer();
            offer2.setCategoryEstateEnum(CategoryEstateEnum.HOUSE);
            offer2.setType(DealType.SELL);
            offer2.setName("House with 3 bedrooms");
            offer2.setPrice(new BigDecimal(300000));
            offer2.setRooms(6);
            offer2.setFloor(3);
            offer2.setM2(450.00);
            offer2.setYear(2012);
            offer2.setRegion(RegionEnum.Blagoevgrad);
            offer2.setTown("Сандански");
            offer2.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/Ranch_style_home_in_Salinas%2C_California.JPG/375px-Ranch_style_home_in_Salinas%2C_California.JPG");
            offer2.setDescription("I don't want description again");
            offer2.setSeller(userRepository.findByUsername("rsolakov")
                    .orElse(null));

            offerRepository.saveAll(List.of(offer1, offer2));
        }


    }

    @Override
    public List<OfferView> getAllOffers() {
        return offerRepository.
                findAll().
                stream().
                map(this::map).
                collect(Collectors.toList());
    }

    @Override
    public DetailsView findById(Long id, String currentUser) {
        DetailsView detailsView = this.offerRepository.
                findById(id).
                map(o -> mapDetailsView(currentUser, o))
                .get();
        return detailsView;
    }


    @Override
    public OfferAddServiceModel addOffer(OfferAddBindingModel offerAddBindingModel, String ownerId) {
        User user = userRepository.findByUsername(ownerId).orElseThrow();
        OfferAddServiceModel offerAddServiceModel = modelMapper.map(offerAddBindingModel, OfferAddServiceModel.class);
        Offer offer = modelMapper.map(offerAddServiceModel, Offer.class);
        offer.setSeller(user);


        Offer savedOffer = offerRepository.save(offer);
        return modelMapper.map(savedOffer, OfferAddServiceModel.class);
    }

    @Override
    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    @Override
    public boolean isOwner(String userName, Long id) {
        Optional<Offer> offerOpt = offerRepository.
                findById(id);
        Optional<User> caller = userRepository.
                findByUsername(userName);

        if (offerOpt.isEmpty() || caller.isEmpty()) {
            return false;
        } else {
            Offer offerEntity = offerOpt.get();

            return isAdmin(caller.get()) ||
                    offerEntity.getSeller().getUsername().equals(userName);
        }
    }

    @Override
    public void updateOffer(OfferUpdateServiceModel serviceModel) throws ObjectNotFoundException {
        Offer offer =
                offerRepository.findById(serviceModel.getId()).orElseThrow(() ->
                        new ObjectNotFoundException("Offer with id " + serviceModel.getId() + " not found!"));

        offer.setName(serviceModel.getName());
        offer.setPrice(serviceModel.getPrice());
        offer.setRooms(serviceModel.getRooms());
        offer.setFloor(serviceModel.getFloor());
        offer.setM2(serviceModel.getM2());
        offer.setDescription(serviceModel.getDescription());
        offer.setType(serviceModel.getType());
        offer.setImageUrl(serviceModel.getImageUrl());
        offer.setTown(serviceModel.getTown());
        offer.setRegion(serviceModel.getRegion());
        offer.setYear(serviceModel.getYear());

        offerRepository.save(offer);
    }


    private boolean isAdmin(User user) {
        return user.
                getRoles().
                stream().
                map(UserRole::getRole).
                anyMatch(r -> r == UserRoleEnum.ADMIN);
    }

    private OfferView map(Offer offer) {
        OfferView view = this.modelMapper.map(offer, OfferView.class);

        return view;
    }

    private DetailsView mapDetailsView(String currentUser, Offer offer) {
        DetailsView detailsView = this.modelMapper.map(offer, DetailsView.class);
        detailsView.setCanDelete(isOwner(currentUser, offer.getId()));
        detailsView.setSellerUsername(offer.getSeller().getUsername());
        return detailsView;
    }
}
