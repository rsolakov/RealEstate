package com.example.realestate.init;

import com.example.realestate.service.EstateModelService;
import com.example.realestate.service.NewsService;
import com.example.realestate.service.OfferService;
import com.example.realestate.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private final UserService userService;
    private final EstateModelService estateModelService;
    private final OfferService offerService;
    private final NewsService newsService;

    public DBInit(UserService userService, EstateModelService estateModelService, OfferService offerService, NewsService newsService) {
        this.userService = userService;
        this.estateModelService = estateModelService;
        this.offerService = offerService;
        this.newsService = newsService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.initializeUsersAndRoles();
        estateModelService.initializeEstateModels();
        offerService.initializeOffers();
        newsService.initializeNews();


    }
}
