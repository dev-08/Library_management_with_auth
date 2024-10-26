package com.library.library_management_system.controller;


import com.library.library_management_system.entities.Users;
import com.library.library_management_system.security.JWTutil;
import com.library.library_management_system.security.JwtRequest;
import com.library.library_management_system.security.JwtResponse;
import com.library.library_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UsersController {
        @Autowired
        private JWTutil jwtutil;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private UserService userService;

//        @PostMapping("/login")
//        public ResponseEntity<?> login(@RequestBody JwtRequest user){
//                JwtResponse jwtResponse = new JwtResponse();
//try {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//        String username= authentication.getName();
//        jwtResponse.setToken(jwtutil.generateToken(username));
//                }catch (Exception e){
//                        e.printStackTrace();
//                }
//
//                return  ResponseEntity.ok(jwtResponse);
//
//        }





        @PostMapping("/login")

        public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest user) throws Exception {

//                try {
//
//                        authenticationManager.authenticate(
//
//                                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
//
//                        );
//
//                } catch (Exception e) {
//
//                        throw new Exception("Incorrect username or password", e);
//
//                }

                System.out.println(user.getUsername());
                System.out.println(user.getPassword());
                        return ResponseEntity.ok(new JwtResponse(jwtutil.generateToken(user.getUsername())));
        }


        @PostMapping("/register")
        public ResponseEntity<?> register(@RequestBody Users user){

                Users users = userService.addUser(user);
                return  ResponseEntity.ok(users);
        }

}
