package com.knucse.authentication.security.filter;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.knucse.authentication.jwt.provider.JwtAuthentication;
import com.knucse.authentication.jwt.provider.JwtTokenValidator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends GenericFilterBean {
    private final JwtTokenValidator jwtTokenValidator;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        if ("OPTIONS".equalsIgnoreCase(servletRequest.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        String accessToken = JwtTokenValidator.resolveToken(servletRequest);
        if (accessToken == null) {
            chain.doFilter(request, response);
            return;
        }

        try {
            Jws<Claims> claims = jwtTokenValidator.extractClaims(accessToken);
            String email = jwtTokenValidator.extractEmail(claims);

            JwtAuthentication jwtAuthentication = JwtAuthentication.of(email, accessToken);
            SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
        } catch (ExpiredJwtException exception) {
            log.warn("JWT token has expired: {}", exception.getMessage());
            servletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "JWT token has expired");
            return;
        } catch (JwtException exception) {
            log.warn("Invalid JWT token: {}", exception.getMessage());
            servletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid JWT token");
            return;
        } catch (RuntimeException exception) {
            log.error("Unexpected error during JWT processing: {}", exception.getMessage(), exception);
            servletResponse.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error");
            return;
        }

        chain.doFilter(request, response);
    }
}
