package com.example.realestate.service.impl;

import com.example.realestate.model.entity.User;
import com.example.realestate.model.entity.UserRole;
import com.example.realestate.model.entity.enums.UserRoleEnum;
import com.example.realestate.model.service.UserRegistrationServiceModel;
import com.example.realestate.repository.UserRepository;
import com.example.realestate.repository.UserRoleRepository;
import com.example.realestate.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final EstateUserServiceImpl estateUserService;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository, EstateUserServiceImpl estateUserService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.estateUserService = estateUserService;
    }

    @Override
    public void initializeUsersAndRoles() {
        initializeRoles();
        initializeUsers();
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {
            UserRole roleAdmin = userRoleRepository.findByRole(UserRoleEnum.ADMIN);
            UserRole roleUser = userRoleRepository.findByRole(UserRoleEnum.USER);

            User admin = new User();
            admin.setUsername("rsolakov");
            admin.setFirstName("Rosen");
            admin.setLastName("Solakov");
            admin.setEmail("rsolakov@gmail.com");
            admin.setPassword(passwordEncoder.encode("12345"));
            admin.setRoles(Set.of(roleAdmin,roleUser));
            userRepository.save(admin);

        }
    }

    private void initializeRoles() {
        if (userRoleRepository.count() == 0) {
            UserRole adminRole = new UserRole();
            adminRole.setRole(UserRoleEnum.ADMIN);

            UserRole userRole = new UserRole();
            userRole.setRole(UserRoleEnum.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));


        }
    }

    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel) {

        UserRole userRole = userRoleRepository.findByRole(UserRoleEnum.USER);

        User newUser = new User();

        newUser.setUsername(userRegistrationServiceModel.getUsername());
        newUser.setFirstName(userRegistrationServiceModel.getFirstName());
        newUser.setLastName(userRegistrationServiceModel.getLastName());
        newUser.setEmail(userRegistrationServiceModel.getEmail());
        newUser.setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword()));
        newUser.setRoles(Set.of(userRole));

        newUser = userRepository.save(newUser);

        UserDetails principal = estateUserService.loadUserByUsername(newUser.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.
                getContext().
                setAuthentication(authentication);
    }


}
