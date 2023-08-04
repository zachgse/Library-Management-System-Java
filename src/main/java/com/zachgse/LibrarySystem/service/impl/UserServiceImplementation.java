package com.zachgse.LibrarySystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zachgse.LibrarySystem.model.User;
import com.zachgse.LibrarySystem.repository.UserRepository;
import com.zachgse.LibrarySystem.service.UserService;

@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User store(User user) {
		user.setRole("ROLE_USER");
		user.setStatus(true);
		return userRepository.save(user);
	}
	
	@Override
	public User view(int id) {
		return userRepository.findById(id).get();
	}

}
