package com.example.realestate.service.impl;

import com.example.realestate.model.entity.News;
import com.example.realestate.model.entity.User;
import com.example.realestate.repository.NewsRepository;
import com.example.realestate.repository.UserRepository;
import com.example.realestate.service.NewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NewsServiceImplTest {

    private News testNews, testNews1;
    private User author, author1;

    @Mock
    private  NewsRepository mockNewsRepository;
    @Mock
    private UserRepository mockUserRepository;
    private NewsService serviceToTest;

    @BeforeEach
    void init() {

        author = new User();
        author.setUsername("rsolakov");

        this.testNews = new News();
        testNews.setDescription("Without description");
        testNews.setTitle("First one");
        testNews.setAuthor(author);
        testNews.setDate(LocalDateTime.now());

        author1 = new User();
        author1.setUsername("rsolakov1");

        this.testNews1 = new News();
        testNews1.setDescription("With description");
        testNews1.setTitle("Second one");
        testNews1.setAuthor(author1);
        testNews1.setDate(LocalDateTime.now());


        serviceToTest = new NewsServiceImpl(mockNewsRepository, new ModelMapper(), mockUserRepository);
    }

    
}