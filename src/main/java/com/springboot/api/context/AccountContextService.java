package com.springboot.api.context;

import com.springboot.api.account.domain.Account;
import com.springboot.api.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * AccountContextService 
 * Created by TaeHyeong Kim on 2020-07-26
**/
@Component(value = "accountContextService")
@RequiredArgsConstructor
public class AccountContextService implements UserDetailsService {

  private final AccountService accountService;

  @Override
  public UserDetails loadUserByUsername(final String loginId) throws UsernameNotFoundException {
    return getAccountContext(accountService.getAccountByLoginId(loginId));
  }

  private AccountContext getAccountContext(final Account account) {
    return AccountContext.fromAccountModel(account);
  }

}
