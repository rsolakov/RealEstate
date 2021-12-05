package com.example.realestate.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import java.util.Optional;

import com.example.realestate.model.entity.User;
import com.example.realestate.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    private static final String TEST_USER_USERNAME = "rsolakov";
    private static final String TEST_USER_EMAIL = "rsolakov@gmail.com";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;


    @Test
    void testOpenRegisterForm() throws Exception {
        mockMvc.
                perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }


    @Test
    void testRegisterUser() throws Exception {
        mockMvc.perform(post("/users/register").
                param("firstName","Rosen").
                param("lastName", "Solakov").
                param("email",TEST_USER_EMAIL).
                param("username",TEST_USER_USERNAME).
                param("password","12345").
                with(csrf()).
                contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).
                andExpect(status().is3xxRedirection());

        Assertions.assertEquals(1, userRepository.count());

        Optional<User> newlyCreatedUserOpt = userRepository.findByUsername(TEST_USER_USERNAME);

        Assertions.assertTrue(newlyCreatedUserOpt.isPresent());

        User newlyCreatedUser = newlyCreatedUserOpt.get();

        Assertions.assertEquals(TEST_USER_EMAIL, newlyCreatedUser.getEmail());
        Assertions.assertEquals(TEST_USER_USERNAME, newlyCreatedUser.getUsername());
        Assertions.assertEquals("Rosen", newlyCreatedUser.getFirstName());
        Assertions.assertEquals("Solakov", newlyCreatedUser.getLastName());
    }




}