package com.example.realestate.web;

import com.example.realestate.model.entity.User;
import com.example.realestate.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class UserLoginTest {


    private static final String TEST_USER_USERNAME = "rsolakov";


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testOpenLoginForm() throws Exception {
        mockMvc.
                perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void testLoginUser() throws Exception {
        mockMvc.perform(post("/users/login").
                param("username", TEST_USER_USERNAME).
                param("password", "12345").
                with(csrf()).
                contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).
                andExpect(status().is3xxRedirection());

        Assertions.assertEquals(1, userRepository.count());

        Optional<User> newlyLoginUserOpt = userRepository.findByUsername(TEST_USER_USERNAME);

        Assertions.assertTrue(newlyLoginUserOpt.isPresent());

        User newlyLoginUser = newlyLoginUserOpt.get();

        Assertions.assertEquals(TEST_USER_USERNAME, newlyLoginUser.getUsername());

    }

}