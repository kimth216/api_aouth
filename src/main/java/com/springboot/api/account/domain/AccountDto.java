package com.springboot.api.account.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * AccountDto 
 * Created by TaeHyeong Kim on 2020-07-24
**/
public class AccountDto {

  @Getter
  @Setter
  public static class AccountInfo {
    private String loginId;
    private Role role;
  }
}
