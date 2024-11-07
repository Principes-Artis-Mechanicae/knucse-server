package com.knucse.authentication.security.dto;

import com.knucse.authentication.application.entity.OAuthUserInfo;

public interface Oauth2ResponseDto {
    String getProvider();
    String getProviderId();
    String getEmail();
    String getName();
    OAuthUserInfo toEntity();
}
