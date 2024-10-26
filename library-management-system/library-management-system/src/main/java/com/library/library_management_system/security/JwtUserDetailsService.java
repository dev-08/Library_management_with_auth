package com.library.library_management_system.security;

import com.library.library_management_system.entities.Users;
import com.library.library_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException  {

        Users user = userRepository.findByusername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }


        return new User(user.getUserName(),user.getPassword(),new ArrayList<>());
    }
}
