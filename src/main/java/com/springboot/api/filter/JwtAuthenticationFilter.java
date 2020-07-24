package com.springboot.api.filter;

import com.springboot.api.handler.JwtAuthenticationFailureHandler;
import com.springboot.api.jwt.HeaderTokenExtractor;
import com.springboot.api.token.JwtPreProcessingToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JwtAuthenticationFilter 
 * Created by TaeHyeong Kim on 2020-07-24
**/
@Slf4j
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  private JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler;

  private HeaderTokenExtractor headerTokenExtractor;

  public JwtAuthenticationFilter(final RequestMatcher requestMatcher,
                                 final JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler,
                                 final HeaderTokenExtractor headerTokenExtractor) {

    super(requestMatcher);
    this.jwtAuthenticationFailureHandler = jwtAuthenticationFailureHandler;
    this.headerTokenExtractor = headerTokenExtractor;
  }

  @Override
  public Authentication attemptAuthentication(final HttpServletRequest req,
                                              final HttpServletResponse res) throws AuthenticationException {

    String tokenPayload = req.getHeader("Authorization");
    JwtPreProcessingToken token = new JwtPreProcessingToken(this.headerTokenExtractor.extract(tokenPayload));
    return super.getAuthenticationManager().authenticate(token);
  }

  @Override
  protected void successfulAuthentication(final HttpServletRequest req,
                                          final HttpServletResponse res,
                                          final FilterChain chain,
                                          final Authentication authResult) throws IOException, ServletException {

    SecurityContext context = SecurityContextHolder.createEmptyContext();
    context.setAuthentication(authResult);
    SecurityContextHolder.setContext(context);
    chain.doFilter(req, res);
  }

  @Override
  protected void unsuccessfulAuthentication(final HttpServletRequest req,
                                            final HttpServletResponse res,
                                            final AuthenticationException failed) throws IOException, ServletException {

    SecurityContextHolder.clearContext();
    this.jwtAuthenticationFailureHandler.onAuthenticationFailure(req, res, failed);
  }

}
