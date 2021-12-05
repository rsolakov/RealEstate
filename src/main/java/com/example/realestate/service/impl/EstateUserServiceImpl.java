package com.example.realestate.service.impl;


import com.example.realestate.model.entity.User;
import com.example.realestate.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstateUserServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  public EstateUserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


    User user =
        userRepository.findByUsername(username).
            orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found!"));

    return mapToUserDetails(user);
  }

  private static UserDetails mapToUserDetails(User user) {


    List<GrantedAuthority> auhtorities =
            user.
            getRoles().
            stream().
            map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name())).
            collect(Collectors.toList());

  
    return new com.example.realestate.service.impl.EstateUser(
            user.getUsername(),
            user.getPassword(),
        auhtorities
    );
  }
}
