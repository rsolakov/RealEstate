package com.example.realestate.service.impl;

import com.example.realestate.repository.NewsRepository;
import com.example.realestate.repository.OfferRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScheduleTasks  {

    private final OfferRepository offerRepository;
    private final NewsRepository newsRepository;


    public ScheduleTasks(OfferRepository offerRepository, NewsRepository newsRepository) {
        this.offerRepository = offerRepository;
        this.newsRepository = newsRepository;
    }

    @Scheduled(fixedDelay = 36000000, initialDelay = 40000000)
    public void showCountOfOffers (){
        System.out.println("The count of offers are " + offerRepository.count() + " at " + LocalDateTime.now());
    }

    @Scheduled(fixedDelay = 36000000, initialDelay = 40000000)
    public void showCountOfNews (){
        System.out.println("There are " + newsRepository.count() + " at " + LocalDateTime.now());
    }


}
