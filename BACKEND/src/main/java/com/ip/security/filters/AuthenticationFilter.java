package com.ip.security.filters;

import com.ip.domain.AppUser;
import com.ip.domain.Role;
import com.ip.rest.util.DateUtil;
import com.ip.security.util.TokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.ip.security.util.SecurityConstants.LOGIN_URL;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private TokenProvider tokenProvider;


    public AuthenticationFilter(AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        setFilterProcessesUrl(LOGIN_URL);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            AppUser credentials = new ObjectMapper().readValue(req.getInputStream(), AppUser.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUsername(),
                            credentials.getPassword(),
                            getGrantedAuthorities(credentials.getRoles()))
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {


        String token = tokenProvider.createToken(auth);


        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(200);

        JSONObject responseBody = new JSONObject();
        responseBody.put("timestamp", DateUtil.getTimestamp());
        responseBody.put("status", 200);
        responseBody.put("message", "Token granted");
        responseBody.put("token", token);
        res.getWriter().write(responseBody.toString(4));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req,
                                              HttpServletResponse res,
                                              AuthenticationException exception) throws IOException {

        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(401);
        JSONObject responseBody = new JSONObject();
        responseBody.put("timestamp", DateUtil.getTimestamp());
        responseBody.put("status", 401);
        responseBody.put("message", "Invalid credentials");
        res.getWriter().write(responseBody.toString(4));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Collection<Role> roles) {

        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
