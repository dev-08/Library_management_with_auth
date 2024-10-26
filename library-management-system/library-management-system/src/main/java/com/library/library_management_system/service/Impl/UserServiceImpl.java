package com.library.library_management_system.service.Impl;

import com.library.library_management_system.entities.Users;
import com.library.library_management_system.repository.UserRepository;
import com.library.library_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Users addUser(Users user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public Users findUserByEmail(String userName) {
        return userRepository.findByusername(userName);
    }
}
