package com.example.myworks.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    static final String jwtToken = "abcd1234!@#$%^&*()_+~{}[]|<>?;:'\",./";

    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(
            jwtToken.getBytes(StandardCharsets.UTF_8)
    );

    public static String createToken(Integer id,String userName){
        Map<String,Object> claims=new HashMap<>();
        claims.put("id",id);
        claims.put("username",userName);
        JwtBuilder jwtBuilder= Jwts.builder()
                .signWith(SECRET_KEY,SignatureAlgorithm.HS256)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+24*60*60*1000));
        String token=jwtBuilder.compact();
        return token;
    }

    public static Integer getIdFromJwt(String token){
        Claims claims=Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return (Integer) claims.get("id");
    }

    public static String getNameFromJwt(String token){
        Claims claims=Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return (String) claims.get("username");
    }

    public static boolean checkToken(String token){
        try{
            Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}