package com.knucse.authentication.application.exception;

import com.knucse.common.exception.support.business.NotFoundException;

public class OAuth2UserInfoNotFoundException extends NotFoundException {
	private static final String code = "OAUTH2_USER_INFO_NOT_FOUND";

	public OAuth2UserInfoNotFoundException() {
		super(code);
	}
}
