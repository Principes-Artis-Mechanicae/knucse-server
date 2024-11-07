package com.knucse.authentication.jwt.provider;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtTokenValidator {
	@Value("${app.jwt.secret}")
	private String secret;

	private SecretKey signKey;

	@PostConstruct
	public void init() {
		this.signKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
	}

	public Jws<Claims> extractClaims(String tokenValue) {
		return Jwts.parser()
			.verifyWith(signKey)
			.build()
			.parseSignedClaims(tokenValue);
	}

	public String extractEmail(Jws<Claims> claimsJws) {
		return claimsJws.getPayload().get("email", String.class);
	}

	public static String resolveToken(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("Authorization-AccessToken".equals(cookie.getName())) {
					return cookie.getValue().replace("Bearer", "").trim();
				}
			}
		}
		return null;
	}
}
