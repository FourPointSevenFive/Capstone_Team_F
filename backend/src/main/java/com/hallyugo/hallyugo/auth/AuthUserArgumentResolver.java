package com.hallyugo.hallyugo.auth;

import com.hallyugo.hallyugo.auth.annotation.AuthUser;
import com.hallyugo.hallyugo.user.domain.User;
import com.hallyugo.hallyugo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer";
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAuthAnnotation = parameter.hasParameterAnnotation(AuthUser.class);
        boolean isUserType = User.class.isAssignableFrom(parameter.getParameterType());

        return hasAuthAnnotation && isUserType;
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {

        String authorizationHeader = webRequest.getHeader(AUTHORIZATION_HEADER);

        if(authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)) {
            String token = authorizationHeader.substring(7);

            if(jwtProvider.validateToken(token)) {
                Authentication authentication = jwtProvider.getAuthentication(token);
                Long userId = Long.parseLong(authentication.getName());
                log.info("userId = {}", userId);

                return userRepository.findById(userId)
                        .orElseThrow(() -> new IllegalArgumentException("User not found"));
            }
        }

        throw new IllegalArgumentException("Invalid or missing token");
    }
}
