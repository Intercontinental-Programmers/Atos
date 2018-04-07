package com.ip.security.filters;

import com.ip.security.util.TokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.ip.security.util.SecurityConstants.HEADER_STRING;

public class AuthorizationFilter extends BasicAuthenticationFilter{

	private final TokenProvider tokenProvider;

	public AuthorizationFilter(AuthenticationManager authManager, TokenProvider tokenProvider) {
		super(authManager);
		this.tokenProvider = tokenProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req,
									HttpServletResponse res,
									FilterChain chain) throws IOException, ServletException {

		if (req.getMethod().equals("OPTIONS"))
			chain.doFilter(req, res);

		String token = req.getHeader(HEADER_STRING);

		if (tokenProvider.validateToken(token)) {

			SecurityContextHolder
					.getContext()
					.setAuthentication(tokenProvider.authenticateToken(token));
		}

		chain.doFilter(req, res);
		resetAuthenticationAfterRequest();
	}

	private void resetAuthenticationAfterRequest() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}
}
