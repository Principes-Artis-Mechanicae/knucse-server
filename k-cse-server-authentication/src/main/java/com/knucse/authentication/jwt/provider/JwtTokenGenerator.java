package com.knucse.authentication.jwt.provider;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.knucse.authentication.config.JwtProperties;
import com.knucse.authentication.jwt.dto.Token;
import com.knucse.authentication.jwt.dto.TokenType;

import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenGenerator {
	private final JwtProperties jwtProperties;

	private SecretKey signKey;

	@PostConstruct
	public void init() {
		signKey = new SecretKeySpec(jwtProperties.getSecret().getBytes(), "HmacSHA256");
	}

	public Token generateToken(JwtAuthentication jwtAuthentication, TokenType type) {
		if (type == TokenType.ACCESS_TOKEN) {
			return doGenerateToken(jwtAuthentication, TokenType.ACCESS_TOKEN);
		} else if (type == TokenType.REFRESH_TOKEN) {
			return doGenerateToken(jwtAuthentication, TokenType.REFRESH_TOKEN);
		}

		throw new IllegalArgumentException("Unsupported token type: " + type);
	}

	private Token doGenerateToken(JwtAuthentication jwtAuthentication, TokenType type) {
		String token = Jwts.builder()
			.header().add(buildHeader(type)).and()
			.claims(buildPayload(jwtAuthentication))
			.expiration(buildExpiration(jwtProperties.getExpiration(type)))
			.signWith(signKey)
			.compact();

		return new Token(jwtProperties.getBearerType(), TokenType.ACCESS_TOKEN, token);
	}

	private Map<String, Object> buildHeader(TokenType type) {
		return Map.of(
			"typ", "JWT",
			"cat", type.name(),
			"alg", "HS256",
			"regDate", System.currentTimeMillis()
		);
	}

	private Map<String, Object> buildPayload(JwtAuthentication jwtAuthentication) {
		return Map.of(
			"email", jwtAuthentication.getName()
		);
	}

	private Date buildExpiration(Integer expirationSeconds) {
		return new Date(System.currentTimeMillis() + expirationSeconds * 1000);
	}
}
