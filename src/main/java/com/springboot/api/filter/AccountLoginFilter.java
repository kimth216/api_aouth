package com.springboot.api.filter;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.springboot.api.LoginDto;
import com.springboot.api.token.AccountPreAuthorizationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AccountLoginFilter 
 * Created by TaeHyeong Kim on 2020-07-23
**/
public class AccountLoginFilter extends AbstractAuthenticationProcessingFilter {

  private AuthenticationSuccessHandler authenticationSuccessHandler;

  private AuthenticationFailureHandler authenticationFailureHandler;

  public AccountLoginFilter(final String defaultFilterProcessesUrl,
                            final AuthenticationSuccessHandler authenticationSuccessHandler,
                            final AuthenticationFailureHandler authenticationFailureHandler) {
    super(defaultFilterProcessesUrl);
    this.authenticationSuccessHandler = authenticationSuccessHandler;
    this.authenticationFailureHandler = authenticationFailureHandler;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
                                              HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
    LoginDto dto = new ObjectMapper().readValue(request.getReader(), LoginDto.class);
    AccountPreAuthorizationToken token = new AccountPreAuthorizationToken(dto);
    return super.getAuthenticationManager().authenticate(token);
  }

  @Override
  protected void successfulAuthentication(final HttpServletRequest req,
                                          final HttpServletResponse res,
                                          final FilterChain chain,
                                          final Authentication authResult) throws IOException, ServletException {
    this.authenticationSuccessHandler.onAuthenticationSuccess(req, res, authResult);
  }

  @Override
  protected void unsuccessfulAuthentication(final HttpServletRequest req,
                                            final HttpServletResponse res,
                                            final AuthenticationException failed) throws IOException, ServletException {
    this.authenticationFailureHandler.onAuthenticationFailure(req, res, failed);
  }


}
