package com.library.library_management_system.service;


import com.library.library_management_system.entities.Users;
import org.springframework.stereotype.Service;


@Service
public interface UserService {


    Users addUser(Users user);
    Users findUserByEmail(String email);
}
