package com.example.realestate.service;


import com.example.realestate.model.view.NewsView;

import java.util.List;

public interface NewsService {

    void initializeNews();

    List<NewsView> getAllNews();

    void deleteNews(Long id);

    NewsView findById(Long id, String currentUser);

    boolean isOwner(String userName, Long id);
}
