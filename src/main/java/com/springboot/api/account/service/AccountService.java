package com.springboot.api.account.service;

import com.springboot.api.account.domain.Account;
import com.springboot.api.account.domain.AccountDto;

/**
 * Created by kth on 2020-07-24
 */

public interface AccountService {

  Account getAccountOrNullByLoginId(String loginId);

  Account updateAccountLoginFailCountAndLock(Account account,
                                             int loginFailCount);

  Account getAccountByLoginId(String loginId);

  void resetAccountLoginFailCount(Account account);

  void createAccount(AccountDto.CreateAccount createAccount);

  //
  boolean isExistsByLoginId(String loginId);

}
