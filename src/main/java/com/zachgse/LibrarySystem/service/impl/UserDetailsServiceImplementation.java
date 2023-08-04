package com.zachgse.LibrarySystem.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zachgse.LibrarySystem.model.User;
import com.zachgse.LibrarySystem.repository.UserRepository;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<User>user = userRepository.findByUsername(username);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return user.map(MyUserDetails::new).get();
    }

}
