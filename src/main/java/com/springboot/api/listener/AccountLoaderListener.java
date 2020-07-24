package com.springboot.api.listener;



import com.springboot.api.account.domain.Account;
import com.springboot.api.account.domain.Role;
import com.springboot.api.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;

/**
 * AccountLoaderListener
 * Created by TaeHyeong Kim on 2020-07-23
**/
@Component
@RequiredArgsConstructor
public class AccountLoaderListener implements ApplicationListener<ApplicationStartedEvent>, Ordered {

  private final AccountRepository accountRepository;

  private final PasswordEncoder passwordEncoder;


  @Override
  public int getOrder() {
    return 0;
  }

  @Override
  public void onApplicationEvent(@Nullable ApplicationStartedEvent event) {

    Account account = new Account();
    account.setLoginId("admin");
    account.setPassword(passwordEncoder.encode("1234"));
    account.setUsername("관리자");
    account.setRole(Role.ADMIN);
    account.setPhone("01011112222");
    account.setEmail("daum@daum.com");
    account.setEnable(true);
    createAccountAssertNull(account);

    Account account1 = new Account();
    account1.setLoginId("user");
    account1.setPassword(passwordEncoder.encode("1234"));
    account1.setUsername("김태형");
    account1.setPhone("01033334444");
    account1.setEmail("naver@naver.com");
    account1.setRole(Role.USER);
    account1.setEnable(true);

    createAccountAssertNull(account1);

    Account account2 = new Account();
    account2.setLoginId("user2");
    account2.setPassword(passwordEncoder.encode("1234"));
    account2.setUsername("김지현");
    account2.setPhone("01055556666");
    account2.setEmail("google@Gmail.com");
    account2.setRole(Role.USER);
    account2.setEnable(true);

    createAccountAssertNull(account2);
  }

  private void createAccountAssertNull(Account create) {
    if (!accountRepository.findByLoginId(create.getLoginId()).isPresent()) {
      accountRepository.save(create);
    }
  }

}
