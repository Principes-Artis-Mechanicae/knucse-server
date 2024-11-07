package com.knucse.authentication.security.details;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.knucse.authentication.application.entity.OAuthUserInfo;
import com.knucse.authentication.application.service.AuthenticationService;
import com.knucse.authentication.security.dto.Oauth2ResponseDto;
import com.knucse.authentication.security.dto.Oauth2ResponseMatcher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
	private final AuthenticationService authenticationService;
	private final Oauth2ResponseMatcher oauth2ResponseMatcher;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		String registrationId = userRequest.getClientRegistration().getRegistrationId();

		Oauth2ResponseDto oauth2Response = oauth2ResponseMatcher.matcher(registrationId, oAuth2User);
		OAuthUserInfo oAuthUserInfo = authenticationService.saveOrReadOauth2UserInfo(oauth2Response);
		return PrincipalDetails.buildPrincipalDetails(oAuthUserInfo, oAuth2User);
	}
}
