package com.knucse.authentication.security.handler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.knucse.authentication.application.service.AuthenticationService;
import com.knucse.authentication.config.JwtProperties;
import com.knucse.authentication.config.OAuth2Properties;
import com.knucse.authentication.jwt.dto.Token;
import com.knucse.authentication.jwt.dto.TokenType;
import com.knucse.authentication.jwt.provider.JwtAuthentication;
import com.knucse.authentication.jwt.provider.JwtTokenGenerator;
import com.knucse.authentication.security.details.PrincipalDetails;
import com.knucse.common.util.cookie.CookieUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@RequiredArgsConstructor
public class Oauth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenGenerator jwtTokenGenerator;
    private final OAuth2Properties oauth2Properties;
    private final JwtProperties jwtProperties;

    private final AuthenticationService authenticationService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        if (!(authentication instanceof JwtAuthentication jwtAuthentication)) {
            log.error("Authentication in SecurityContextHolder is not of type JwtAuthentication");
            // Optionally, handle this case, e.g., throw an exception or generate a new JwtAuthentication
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Invalid authentication type");
            return;
        }

		Token accessToken = jwtTokenGenerator.generateToken(jwtAuthentication, TokenType.ACCESS_TOKEN);

        Cookie accessCookie = CookieUtil.createCookie(
            accessToken.tokenType().name(),
            accessToken.value(),
            jwtProperties.getExpiration().getAccess(),
            "/",
            true,
            true
        );

        response.addCookie(accessCookie);

        // 사용자와 학생이 연결되어 있는지
        boolean isConnectedToStudent = authenticationService.isConnectStudent(jwtAuthentication.getName());

        // 프론트엔드의 리다이렉트 URL 설정 from configuration
        String redirectUrl = oauth2Properties.getRedirectUri();

        // 필요한 정보를 쿼리 파라미터로 추가
        redirectUrl += "?isConnectedToStudent=" + isConnectedToStudent;

        // 리다이렉트 수행
        response.sendRedirect(redirectUrl);

        // 로그 출력
        log.info("OAuth2 로그인에 성공하였습니다. 이메일 : {}", jwtAuthentication.getName());
    }
}
