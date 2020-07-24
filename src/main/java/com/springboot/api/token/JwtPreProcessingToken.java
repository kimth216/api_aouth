package com.springboot.api.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * JwtPreProcessingToken 
 * Created by TaeHyeong Kim on 2020-07-24
**/
public class JwtPreProcessingToken extends UsernamePasswordAuthenticationToken {

  private JwtPreProcessingToken(final Object principal,
                                final Object credentials) {
    super(principal, credentials);
  }

  public JwtPreProcessingToken(final String token) {
    this(token, token.length());
  }

}
