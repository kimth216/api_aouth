package com.springboot.api.account.repository;

import com.springboot.api.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * AccountRepository
 * Created by TaeHyeong Kim on 2020-07-23
**/

public interface AccountRepository extends JpaRepository<Account, Long> {
  Optional<Account> findByLoginId(String loginId);
  boolean existsByLoginId(String loginId);


}
