package com.hallyugo.hallyugo.auth.service;

import com.hallyugo.hallyugo.auth.JwtProvider;
import com.hallyugo.hallyugo.auth.domain.RefreshToken;
import com.hallyugo.hallyugo.auth.domain.UserToken;
import com.hallyugo.hallyugo.auth.domain.request.TokenRequestDto;
import com.hallyugo.hallyugo.auth.repository.RefreshTokenRepository;
import com.hallyugo.hallyugo.common.exception.ExceptionCode;
import com.hallyugo.hallyugo.common.exception.InvalidJwtException;
import com.hallyugo.hallyugo.common.exception.LoginException;
import com.hallyugo.hallyugo.user.domain.User;
import com.hallyugo.hallyugo.user.domain.request.UserRequestDto;
import com.hallyugo.hallyugo.user.domain.response.UserResponseDto;
import com.hallyugo.hallyugo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public UserResponseDto signin(UserRequestDto userRequestDto) {
        if(userRepository.existsByUsername(userRequestDto.getUsername())) {
            throw new LoginException(ExceptionCode.DUPLICATE_USER_REGISTRATION);
        }

        User user = userRequestDto.toUser(passwordEncoder);
        return UserResponseDto.of(userRepository.save(user));
    }

    public UserToken login(UserRequestDto userRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = userRequestDto.toAuthentication();

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        UserToken userToken = jwtProvider.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Long userId = Long.parseLong(userDetails.getUsername());
        log.info("userId : {}", userId);

        RefreshToken refreshToken = RefreshToken.builder()
                .userId(userId)
                .value(userToken.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return userToken;
    }

    public UserToken reissue(TokenRequestDto tokenRequestDto) {
        // Refresh Token 유효성 검사
        if(!jwtProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new InvalidJwtException(ExceptionCode.INVALID_REFRESH_TOKEN);
        }

        Authentication authentication = jwtProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 저장소에서 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findById(tokenRequestDto.getRefreshToken())
                .orElseThrow(() -> new LoginException(ExceptionCode.LOGGED_OUT_USER));

        // Refresh Token 일치하는지 검사
        if(!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new LoginException(ExceptionCode.TOKEN_USER_MISMATCH);
        }

        // 기존 Refresh Token 삭제
        refreshTokenRepository.delete(refreshToken);

        // 새로운 토큰 생성
        UserToken userToken = jwtProvider.generateToken(authentication);

        // 새로운 Refresh Token 생성 및 저장소 업데이트
        RefreshToken newRefreshToken = refreshToken.updateToken(userToken.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 새로운 Access Token 및 Refresh Token 반환
        return userToken;
    }

    public void logout(TokenRequestDto tokenRequestDto) {
        // Refresh Token 유효성 검사
        if (!jwtProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new InvalidJwtException(ExceptionCode.INVALID_REFRESH_TOKEN);
        }

        // Refresh Token을 DB에서 찾아 삭제
        RefreshToken refreshToken = refreshTokenRepository.findById(tokenRequestDto.getRefreshToken())
                .orElseThrow(() -> new InvalidJwtException(ExceptionCode.INVALID_REFRESH_TOKEN));

        // Refresh Token 삭제
        refreshTokenRepository.delete(refreshToken);
    }

}
