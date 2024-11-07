package com.knucse.authentication.application.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knucse.authentication.application.entity.OAuthUserInfo;
import com.knucse.authentication.application.exception.OAuth2UserInfoNotFoundException;
import com.knucse.authentication.application.persistence.OAuth2UserInfoRepository;
import com.knucse.authentication.security.dto.Oauth2ResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthenticationService {
	private final OAuth2UserInfoRepository oAuth2UserInfoRepository;


	/**
	 * OAuth2 사용자 정보를 저장하거나 기존 정보를 반환합니다.
	 *
	 * @param responseDto OAuth2 응답 DTO
	 * @return 저장되었거나 기존의 OAuth2 사용자 정보 엔티티
	 */
	@Transactional
	public OAuthUserInfo saveOrReadOauth2UserInfo(Oauth2ResponseDto responseDto) {
		Optional<OAuthUserInfo> oAuth2UserInfoOptional =
			oAuth2UserInfoRepository.findByEmail(responseDto.getEmail());
		return oAuth2UserInfoOptional.orElseGet(() ->
			oAuth2UserInfoRepository.save(responseDto.toEntity()));
	}

	/**
	 * 이메일을 통해 OAuth2 사용자 정보를 조회합니다.
	 *
	 * @param email 조회할 이메일 주소
	 * @return 해당 이메일의 OAuth2 사용자 정보 엔티티
	 * @throws OAuth2UserInfoNotFoundException "OAUTH2_USER_INFO_NOT_FOUND"
	 */
	public OAuthUserInfo findOAuth2UserInfoByEmail(String email) {
		return oAuth2UserInfoRepository.findByEmail(email)
			.orElseThrow(OAuth2UserInfoNotFoundException::new);
	}

	public Boolean isConnectStudent(String email) {
		Optional<OAuthUserInfo> oAuthUserInfo = oAuth2UserInfoRepository.findByEmail(email);
		return oAuthUserInfo.isPresent() && oAuthUserInfo.get().getStudent() != null;
	}
}
