package com.ip.security.util;

import com.ip.services.UserDetailsServiceImpl;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.ip.security.util.SecurityConstants.*;

@Component
public class TokenProvider {

    private static final Logger logger = Logger.getLogger(TokenProvider.class);

    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public TokenProvider(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public boolean validateToken(String token){

        if (token != null && token.startsWith(SecurityConstants.TOKEN_PREFIX)){
            try {

                Jwts.parser()
                        .setSigningKey(SecurityConstants.SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""));

                return true;
            } catch (JwtException e) {
                return false;
            }
        }

        return false;
    }

    public UsernamePasswordAuthenticationToken authenticateToken(String token){

            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (user != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(user);
                return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), "", userDetails.getAuthorities());
            }

            return null;
    }

    public String createToken(Authentication auth) {

        return Jwts.builder()
                .setSubject(((User) auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
}
