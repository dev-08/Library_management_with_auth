package com.library.library_management_system.security;//package com.library.library_management_system.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.stereotype.Component;
//
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//
//
//@Component
//public class JWTutil {
//
//
//
//    private String token = "a3d8f623b5f3e91d41a7e8e937cc9371b22ac8ad9cbd10144ec2b7323c5bb908";
//    private int timeDuration = 1000*60*60;
//
//    public String generateToken(String username){
//        Map<String, Object> map = new HashMap();
//
//        return createToken(map,username);
//    }
//
//    public String createToken( Map<String, Object> map,String subject){
//        return Jwts.builder().setClaims(map).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis()+timeDuration)).signWith(SignatureAlgorithm.HS256,token).compact();
//    }
//
//
//    public boolean validateToken(String token,String username){
//
//        final String extractUserName = extractUsername(token);
//        return (extractUserName.equals(username) && !tokenExpired(token));
//
//    }
//
//
//    public  boolean tokenExpired(String token){
//        return  extractAllClaims(token).getExpiration().before(new Date());
//    }
//
//    public String extractUsername(String token){
//        return extractAllClaims(token).getSubject();
//    }
//
//    public Claims extractAllClaims(String token){
//        return  Jwts.parser().setSigningKey(token).parseClaimsJws(token).getBody();
//    }
//
//
//}





import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JWTutil {

    // Replace this with your securely generated secret key
    private final String SECRET_KEY = "a3d8f623b5f3e91d41a7e8e937cc9371b22ac8ad9cbd10144ec2b7323c5bb908";
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    // Convert the SECRET_KEY string to a Key object
    private Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    // Generate JWT token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, getSigningKey()) // Use Key object
                .compact();
    }

    // Extract username from JWT token
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Check if token is expired
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extract expiration date from token
    private Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    // Validate the token by matching username and checking if expired
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()               // Updated to use parserBuilder()
                .setSigningKey(getSigningKey())    // Use the Key object for signing
                .build()                           // Build the parser
                .parseClaimsJws(token)             // Parse the token
                .getBody();                        // Get the token body (claims)
    }
}

