package com.zachgse.LibrarySystem.service;

import org.springframework.stereotype.Service;

import com.zachgse.LibrarySystem.model.User;

@Service
public interface UserService {
	User store(User user);
	User view (int id);
}
