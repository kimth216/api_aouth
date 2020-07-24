package com.springboot.api.token;

import com.springboot.api.LoginDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Created by kth on 2020-07-24
 */
public class AccountPreAuthorizationToken extends UsernamePasswordAuthenticationToken {

  private AccountPreAuthorizationToken(final String loginId,
                                       final String password) {
    super(loginId, password);
  }

  public AccountPreAuthorizationToken(final LoginDto dto) {
    this(dto.getLoginId(), dto.getPassword());
  }

  public String getLoginId() {
    return (String) super.getPrincipal();
  }

  public String getPassword() {
    return (String) super.getCredentials();
  }

}
