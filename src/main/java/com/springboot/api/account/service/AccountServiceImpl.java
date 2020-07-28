package com.springboot.api.account.service;


import com.springboot.api.account.domain.Account;
import com.springboot.api.account.repository.AccountRepository;
import com.springboot.api.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * AccountServiceImpl 
 * Created by TaeHyeong Kim on 2020-07-28
**/
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  private final PasswordEncoder passwordEncoder;



  @Override
  public Account getAccountOrNullByLoginId(String loginId) {
    return accountRepository.findByLoginId(loginId).orElse(null);
  }

  @Override
  public Account updateAccountLoginFailCountAndLock(Account account, int loginFailCount) {
    account.setLoginFailCount(account.getLoginFailCount() + 1);
    if (account.getLoginFailCount() >= loginFailCount) {
      account.setLocked(true);
    }
    return accountRepository.save(account);
  }

  @Override
  public Account getAccountByLoginId(String loginId) {
    return accountRepository.findByLoginId(loginId).orElseThrow(() -> new BadRequestException("존재하지 않는 사용자 정보입니다."));
  }

  @Override
  public void resetAccountLoginFailCount(Account account) {
    account.setLoginFailCount(0);
    accountRepository.save(account);
  }
}
