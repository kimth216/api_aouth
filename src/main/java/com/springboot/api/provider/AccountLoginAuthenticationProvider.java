package com.springboot.api.provider;

import com.springboot.api.account.domain.Account;
import com.springboot.api.context.AccountContext;
import com.springboot.api.account.service.AccountService;
import com.springboot.api.token.AccountPostAuthorizationToken;
import com.springboot.api.token.AccountPreAuthorizationToken;
import com.springboot.api.util.MsgSourceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * AccountLoginAuthenticationProvider
 * Created by TaeHyeong Kim on 2020-07-23
**/

@Slf4j
@Component("accountLoginAuthenticationProvider")
@RequiredArgsConstructor
public class AccountLoginAuthenticationProvider implements AuthenticationProvider {

  private final PasswordEncoder passwordEncoder;

  private final AccountService accountService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    AccountPreAuthorizationToken token = (AccountPreAuthorizationToken) authentication;

    String loginId = token.getLoginId();
    String password = token.getPassword();

    Account account = accountService.getAccountOrNullByLoginId(loginId);
    System.out.println(account);
    if (account == null) {
      throw new UsernameNotFoundException(MsgSourceUtil.getMsg("account.login.fail"));
    }

    if (account.isLocked()) {
      throw new BadCredentialsException(MsgSourceUtil.getMsg("account.locked"));
    }

    int loginFailCount = 5;

    if (!isCorrectPassword(password, account)) {
      if (accountService.updateAccountLoginFailCountAndLock(account, loginFailCount).isLocked()) {
        throw new BadCredentialsException(MsgSourceUtil.getMsg("account.login.fail.locked", loginFailCount));
      }
      throw new BadCredentialsException(MsgSourceUtil.getMsg("account.login.fail"));
    }

    if (!account.isEnable()) {
      throw new BadCredentialsException(MsgSourceUtil.getMsg("account.disabled"));
    }

    accountService.resetAccountLoginFailCount(account);
    return AccountPostAuthorizationToken.getTokenFromAccountContext(AccountContext.fromAccountModel(account));
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return AccountPreAuthorizationToken.class.isAssignableFrom(authentication);
  }

  private boolean isCorrectPassword(final String password, final Account account) {
    return passwordEncoder.matches(password, account.getPassword());
  }
}
