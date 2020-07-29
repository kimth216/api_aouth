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

  @Getter
  @Setter
  public static class CreateAccount {
    private String loginId;
    private String password;
    private String username;
    private String phone;
    private String email;
    private Role role;
  }
}
