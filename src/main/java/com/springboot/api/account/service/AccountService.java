package com.springboot.api.account.service;

import com.springboot.api.account.domain.Account;

/**
 * Created by kth on 2020-07-24
 */

public interface AccountService {

  Account getAccountOrNullByLoginId(String loginId);

  Account updateAccountLoginFailCountAndLock(Account account,
                                             int loginFailCount);

  Account getAccountByLoginId(String loginId);

  void resetAccountLoginFailCount(Account account);
}
