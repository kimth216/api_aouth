package com.springboot.api.provider;

import com.springboot.api.jwt.JwtDecoder;
import com.springboot.api.context.AccountContext;
import com.springboot.api.account.domain.UserContext;
import com.springboot.api.token.AccountPostAuthorizationToken;
import com.springboot.api.token.JwtPreProcessingToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Created by kth on 2020-07-24
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

  private final JwtDecoder jwtDecoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    UserContext userContext = jwtDecoder.decodeJwt((String)authentication.getPrincipal());

      return AccountPostAuthorizationToken.getTokenFromAccountContext(AccountContext.of(jwtDecoder.decodeJwt((String)authentication.getPrincipal())));
  }

  @Override
  public boolean supports(final Class<?> authentication) {
    return JwtPreProcessingToken.class.isAssignableFrom(authentication);
  }

}
