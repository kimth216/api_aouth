package com.springboot.api.account.service;


import com.springboot.api.account.domain.Account;
import com.springboot.api.account.domain.AccountDto;
import com.springboot.api.account.repository.AccountRepository;
import com.springboot.api.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * AccountServiceImpl 
 * Created by TaeHyeong Kim on 2020-07-28
**/
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  private final PasswordEncoder passwordEncoder;

  private final ModelMapper modelMapper;


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

  @Override
  public void createAccount(AccountDto.CreateAccount createAccount) {

    if (isExistsByLoginId(createAccount.getLoginId())) {
      throw new BadRequestException("이미 사용중인 ID 입니다.");
    }else{
      createAccount.setPassword(passwordEncoder.encode(createAccount.getPassword()));
      accountRepository.save(modelMapper.map(createAccount, Account.class));
    }
  }


  @Override
  public boolean isExistsByLoginId(String loginId) {
    return accountRepository.existsByLoginId(loginId);
  }

}
