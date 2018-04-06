package com.ip.security.util;

public class SecurityConstants {

	public static final String SECRET = "lkd_jash38am51an3-67agdsi971_DAs2938";
	public static final long EXPIRATION_TIME = 3600000; // 24h
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String LOGIN_URL = "/api/auth/login";

	public static final String USER = "ROLE_USER";
	public static final String ADMIN = "ROLE_ADMIN";
}
