package com.knucse.authentication.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.knucse.authentication.jwt.dto.TokenType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Configuration
@ConfigurationProperties(prefix = "app.jwt")
public class JwtProperties {
	private String secret;
	private String bearerType;
	private Expiration expiration;

	@Getter @Setter
	public static class Expiration {
		private int access;
		private int refresh;
	}

	public int getExpiration(TokenType tokenType) {
		return tokenType == TokenType.ACCESS_TOKEN ? expiration.getAccess() : expiration.getRefresh();
	}
}
