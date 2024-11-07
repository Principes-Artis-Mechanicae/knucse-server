package com.knucse.authentication.infra;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.knucse.authentication.jwt.provider.JwtAuthentication;
import com.knucse.common.annotation.LoggedInUser;

import jakarta.annotation.Nonnull;

@Component
public class LoggedInUserArgumentResolver implements HandlerMethodArgumentResolver {
	@Override
	public boolean supportsParameter(@Nonnull MethodParameter parameter) {
		return parameter.hasParameterAnnotation(LoggedInUser.class)
			&& parameter.getParameterType().equals(String.class);
	}

	@Override
	public String resolveArgument(
		@Nonnull MethodParameter parameter,
		ModelAndViewContainer mavContainer,
		@Nonnull NativeWebRequest webRequest,
		WebDataBinderFactory binderFactory
	) {
		Authentication authentication = SecurityContextHolder
			.getContext()
			.getAuthentication();
		if (!(authentication instanceof JwtAuthentication)) {
			throw new Authe();
		}
		return (String) authentication.getPrincipal();
	}
}
