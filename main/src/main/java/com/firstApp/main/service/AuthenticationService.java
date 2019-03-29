package com.firstApp.main.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import static java.util.Collections.emptyList;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationService {

	static final long EXPIRATIONTIME = 864_000_00; // 1 day in milliseconds
	static final String SIGNINGKEY = "SecretKey";
	static final String PREFIX = "Bearer ";
	
	//Add token to Authorization header
	static public void addToken (HttpServletResponse response, String username) {
		String JwtToken = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SIGNINGKEY).compact();
		response.addHeader("Authorization", PREFIX + JwtToken);
		response.addHeader("Access-Control-Expose-Headers", "Authorization");
	}
	
	// Get token from Authorization header
	static public Authentication getAuthentication(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		if (token != null) {
			String user = Jwts.parser()
			.setSigningKey(SIGNINGKEY)
			.parseClaimsJws(token.replace(PREFIX, " "))
			.getBody()
			.getSubject();
		
		if (user != null)
			return new UsernamePasswordAuthenticationToken(user, null, emptyList());
		}
		return null;
		}
}

