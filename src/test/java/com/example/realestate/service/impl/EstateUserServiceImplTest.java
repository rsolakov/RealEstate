package com.example.realestate.service.impl;

import com.example.realestate.model.entity.User;
import com.example.realestate.model.entity.UserRole;
import com.example.realestate.model.entity.enums.UserRoleEnum;
import com.example.realestate.repository.UserRepository;
import com.example.realestate.service.impl.EstateUserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EstateUserServiceImplTest {

    private User testUser;
    private UserRole adminRole, userRole;
    private EstateUserServiceImpl serviceToTest;
    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void init() {

        serviceToTest = new EstateUserServiceImpl(mockUserRepository);

        adminRole = new UserRole();
        adminRole.setRole(UserRoleEnum.ADMIN);

        userRole = new UserRole();
        userRole.setRole(UserRoleEnum.USER);

        testUser = new User();
        testUser.setUsername("rsolakov");
        testUser.setEmail("rsolakov@gmail.com");
        testUser.setPassword("12345");
        testUser.setRoles(Set.of(adminRole, userRole));
    }

    @Test
    void testUserNotFound() {
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("invalid_user_email@not-exist.com")
        );
    }

    @Test
    void testUserFound() {
        Mockito.when(mockUserRepository.findByUsername(testUser.getUsername())).
                thenReturn(Optional.of(testUser));

        UserDetails actual = serviceToTest.loadUserByUsername(testUser.getUsername());

        String expectedRoles = "ROLE_ADMIN, ROLE_USER";
        String actualRoles = actual.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(
                Collectors.joining(", "));

        Assertions.assertEquals(actual.getUsername(), testUser.getUsername());
        Assertions.assertEquals(expectedRoles, actualRoles);
    }

}