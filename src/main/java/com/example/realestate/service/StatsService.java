package com.example.realestate.service;

import com.example.realestate.model.view.StatsView;

public interface StatsService {

    void onRequest();
    StatsView getStats();
}
