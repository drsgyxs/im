package com.drsg.demo.v1.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import java.util.Date;

public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    private static final String TOKEN_SECRET = "asczcwef2vx4ncmmwe3iodcncz";
    private static final long TOKEN_EXPIRATION = 1_209_600_000L;
    private static final String ISSUER = "drsg";
    public static final String PREFIX = "Bearer ";

    public static String createToken(Authentication authentication) {
        return PREFIX + Jwts
                .builder()
                .setSubject(((User) authentication.getPrincipal()).getUsername())
                .setIssuer(ISSUER)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
                .compact();
    }

    public static String getUsername(String authHeader) {
        try {
            String token = authHeader.substring(PREFIX.length());
            return Jwts
                    .parser()
                    .setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            logger.error("token parser error.", e);
            return null;
        }
    }

    public static boolean validateToken(String authHeader) {
        try {
            String token = authHeader.substring(PREFIX.length());
            Jwts
                    .parser()
                    .setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            logger.error("token validate error.", e);
            return false;
        }
    }
}
