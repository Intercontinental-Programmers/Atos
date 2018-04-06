package com.ip.security.errorHandlers;

import com.ip.rest.util.DateUtil;
import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException)
            throws IOException {

        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(403);

        JSONObject responseBody = new JSONObject();
        responseBody.put("timestamp", DateUtil.getTimestamp());
        responseBody.put("status", 403);
        responseBody.put("message", "Access denied");
        res.getWriter().write(responseBody.toString());
    }
}
