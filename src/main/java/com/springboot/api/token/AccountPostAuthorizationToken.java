package com.springboot.api.token;

import com.springboot.api.context.AccountContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by kth on 2020-07-24
 */
public class AccountPostAuthorizationToken extends UsernamePasswordAuthenticationToken {
  public AccountPostAuthorizationToken(final Object principal,
                                       final Object credentials,
                                       final Collection<? extends GrantedAuthority> authorities) {
    super(principal, credentials, authorities);
  }

  public AccountContext getAccountContext() {
    return (AccountContext)super.getPrincipal();
  }

  public static AccountPostAuthorizationToken getTokenFromAccountContext(final AccountContext context) {
    return new AccountPostAuthorizationToken(context, context.getPassword(), context.getAuthorities());
  }

}
