package com.example.realestate.service;


import com.example.realestate.model.service.UserRegistrationServiceModel;

public interface UserService {
    void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel);

    void initializeUsersAndRoles();
}
