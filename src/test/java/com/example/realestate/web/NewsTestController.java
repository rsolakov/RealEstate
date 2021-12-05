package com.example.realestate.web;

import com.example.realestate.repository.NewsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class NewsTestController {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private NewsRepository mockedNewsRepository;


    @Test
    void testAllNews() throws Exception {
        mockMvc.
                perform(get("/news/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("news"));
    }


}

