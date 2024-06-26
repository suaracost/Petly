package com.example.demo.security;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

@Component
public class JWTGenerator {

    public static final long EXPIRATION_TIME = 7000000L;
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(Authentication authentication){
    
        
        /*Datos necesarios para la creacion */
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + EXPIRATION_TIME);

        /* Crear el JWT */
        String token = Jwts.builder().setSubject(username)
            .setIssuedAt(currentDate)
            .setExpiration(expireDate)
            .signWith(key, SignatureAlgorithm.HS512)
            .compact();

        return token;
    }

    public String extractUsername(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try{
            System.out.println("\n \n");
            System.out.println("Token valido: " + token);
            System.out.println("\n \n");
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch(Exception e){
            System.out.println("\n \n");
            System.out.println("Token invalido: " + e.getMessage());
            System.out.println("\n \n");
            return false;
        }
    }
    
}
