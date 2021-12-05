package com.example.realestate.service.impl;

import com.example.realestate.exception.OfferNotFoundException;
import com.example.realestate.model.entity.Offer;
import com.example.realestate.model.entity.User;
import com.example.realestate.model.entity.enums.CategoryEstateEnum;
import com.example.realestate.model.entity.enums.DealType;
import com.example.realestate.model.entity.enums.RegionEnum;
import com.example.realestate.model.service.OfferUpdateServiceModel;
import com.example.realestate.model.view.OfferView;
import com.example.realestate.repository.EstateModelRepository;
import com.example.realestate.repository.OfferRepository;
import com.example.realestate.repository.UserRepository;
import com.example.realestate.service.OfferService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OfferServiceImplTest {

    private Offer testOffer;
    private Offer testOffer1;
    private User seller;
    private User seller1;
    @Mock
    private OfferRepository mockOfferRepository;
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private EstateModelRepository mockEstateModelRepository;
    private OfferService serviceToTest;

    private static final String TEST_OFFER_NAME = "Apartment";

    @BeforeEach
    void init() {

        seller = new User();
        seller.setUsername("rsolakov");

        this.testOffer = new Offer();
        testOffer.setCategoryEstateEnum(CategoryEstateEnum.APARTMENT);
        testOffer.setRegion(RegionEnum.Blagoevgrad);
        testOffer.setType(DealType.SELL);
        testOffer.setName(TEST_OFFER_NAME);
        testOffer.setRooms(4);
        testOffer.setPrice(new BigDecimal(5500));
        testOffer.setFloor(5);
        testOffer.setM2(222.00);
        testOffer.setYear(1999);
        testOffer.setTown("Sandanski");
        testOffer.setDescription("Big apartment for little money");
        testOffer.setSeller(seller);


        seller1 = new User();
        seller1.setUsername("rsolakov1");

        this.testOffer1 = new Offer();
        testOffer1.setCategoryEstateEnum(CategoryEstateEnum.HOUSE);
        testOffer1.setRegion(RegionEnum.Sofia);
        testOffer1.setType(DealType.SELL);
        testOffer1.setName("House");
        testOffer1.setRooms(2);
        testOffer1.setPrice(new BigDecimal(4500));
        testOffer1.setFloor(2);
        testOffer1.setM2(250.00);
        testOffer1.setYear(2000);
        testOffer1.setTown("Sofia");
        testOffer1.setDescription("Big house for little money");
        testOffer1.setSeller(seller1);

        serviceToTest = new OfferServiceImpl(mockOfferRepository, new ModelMapper(), mockEstateModelRepository, mockUserRepository);
    }


    @Test
    public void testGetAll() {

        when(mockOfferRepository.findAll()).thenReturn(List.of(testOffer, testOffer1));

        List<OfferView> allModels = serviceToTest.getAllOffers();

        Assertions.assertEquals(2, allModels.size());

        OfferView model1 = allModels.get(0);
        OfferView model2 = allModels.get(1);

        Assertions.assertEquals(testOffer.getTown(), model1.getTown());
        Assertions.assertEquals(testOffer.getDescription(), model1.getDescription());
        Assertions.assertEquals(testOffer.getFloor(), model1.getFloor());
        Assertions.assertEquals(testOffer.getName(), model1.getName());

        Assertions.assertEquals(testOffer1.getTown(), model2.getTown());
        Assertions.assertEquals(testOffer1.getDescription(), model2.getDescription());
        Assertions.assertEquals(testOffer1.getFloor(), model2.getFloor());
        Assertions.assertEquals(testOffer1.getName(), model2.getName());
    }




    }
