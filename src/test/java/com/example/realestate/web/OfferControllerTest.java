package com.example.realestate.web;

import com.example.realestate.model.entity.Offer;
import com.example.realestate.model.entity.User;
import com.example.realestate.model.entity.enums.CategoryEstateEnum;
import com.example.realestate.model.entity.enums.DealType;
import com.example.realestate.model.entity.enums.RegionEnum;
import com.example.realestate.repository.OfferRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OfferControllerTest {

    private static final String TEST_OFFER_NAME = "Apartment";
    private static final String TEST_OFFER_DESCRIPTION = "Big apartment for little money";
    private static final CategoryEstateEnum TEST_OFFER_CATEGORY = CategoryEstateEnum.APARTMENT;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private OfferRepository offerRepository;

    private Offer testOffer, testOffer1;
    private User seller, seller1;


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


    }


    @Test
    void testAddOffers() throws Exception {
        mockMvc.
                perform(MockMvcRequestBuilders.post("/offers/add")
                        .param("type", "SELL")
                        .param("name", TEST_OFFER_NAME)
                        .param("rooms","4")
                        .param("price","5000")
                        .param("floor","4")
                        .param("m2","250")
                        .param("year","2000")
                        .param("region", "Sofia")
                        .param("town","Sofia")
                        .param("categoryEstateEnum", String.valueOf(TEST_OFFER_CATEGORY))
                        .param("description",TEST_OFFER_DESCRIPTION)
                .with(csrf()))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(2, offerRepository.count());
    }




    @Test
    void deleteOffer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/offers/{id}", 1)
                .with(csrf()))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(2, offerRepository.count());

    }



}
