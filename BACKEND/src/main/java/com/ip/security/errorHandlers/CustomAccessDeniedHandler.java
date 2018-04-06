package com.ip.security.errorHandlers;

import com.ip.rest.util.DateUtil;
import org.json.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException accessDeniedException)
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
