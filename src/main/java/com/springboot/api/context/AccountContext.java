package com.springboot.api.context;

import com.springboot.api.account.domain.Account;
import com.springboot.api.account.domain.Role;
import com.springboot.api.account.domain.UserContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * AccountContext
 * Created by TaeHyeong Kim on 2020-07-24
**/
public class AccountContext extends User {

  private Account account;

  private AccountContext(final Account account, final String loginId, final String password, final Collection<? extends GrantedAuthority> authorities) {
    super(loginId, password, authorities);
    this.account = account;
  }

  private AccountContext(final String loginId, final String password, final String role, final String username) {
    super(loginId, password, parseAuthorities(role));
    this.account = Account.of(loginId, password, Role.getRoleByName(role), username);
  }

  public static AccountContext of(final UserContext userContext) {
    return new AccountContext(userContext.getLoginId(), userContext.getPassword(), userContext.getRole(), userContext.getUsername());
  }

  public static AccountContext fromAccountModel(final Account account) {
    return new AccountContext(account, account.getLoginId(), account.getPassword(), parseAuthorities(account.getRole()));
  }

  private static List<SimpleGrantedAuthority> parseAuthorities(final Role role) {
    return Collections.singletonList(new SimpleGrantedAuthority(role.getRoleName()));
  }

  private static List<SimpleGrantedAuthority> parseAuthorities(final String role) {
    return parseAuthorities(Role.getRoleByName(role));
  }

  public Account getAccount() {
    return account;
  }

}
