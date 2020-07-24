package com.springboot.api.filter;

import com.springboot.api.RefreshTokenDto;
import com.springboot.api.exception.BadRequestException;
import com.springboot.api.handler.AccountJwtAuthenticationSuccessHandler;
import com.springboot.api.token.JwtPreProcessingToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AccountJwtRefreshAuthenticationFilter
 * Created by TaeHyeong Kim on 2020-07-24
**/
@Slf4j
public class AccountJwtRefreshAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  private AccountJwtAuthenticationSuccessHandler accountJwtAuthenticationSuccessHandler;

  private AuthenticationFailureHandler authenticationFailureHandler;

  public AccountJwtRefreshAuthenticationFilter(final String defaultUrl,
                                               final AccountJwtAuthenticationSuccessHandler accountJwtAuthenticationSuccessHandler,
                                               final AuthenticationFailureHandler authenticationFailureHandler) {
    super(defaultUrl);
    this.accountJwtAuthenticationSuccessHandler = accountJwtAuthenticationSuccessHandler;
    this.authenticationFailureHandler = authenticationFailureHandler;
  }

  @Override
  public Authentication attemptAuthentication(final HttpServletRequest req,
                                              final HttpServletResponse res) throws AuthenticationException {

    RefreshTokenDto refreshTokenDto = RefreshTokenDto.of(req);
    JwtPreProcessingToken token = new JwtPreProcessingToken(refreshTokenDto.getRefreshToken());
    if (!refreshTokenDto.getGrantType().equals("refresh_token")) {
      throw new BadRequestException("Unsupported grant type");
    }
    return super.getAuthenticationManager().authenticate(token);
  }

  @Override
  protected void successfulAuthentication(final HttpServletRequest req,
                                          final HttpServletResponse res,
                                          final FilterChain chain,
                                          final Authentication authResult) throws IOException, ServletException {

    this.accountJwtAuthenticationSuccessHandler.onAuthenticationSuccess(req, res, authResult);
  }

  @Override
  protected void unsuccessfulAuthentication(final HttpServletRequest req,
                                            final HttpServletResponse res,
                                            final AuthenticationException failed) throws IOException, ServletException {
    this.authenticationFailureHandler.onAuthenticationFailure(req, res, failed);
  }
}
