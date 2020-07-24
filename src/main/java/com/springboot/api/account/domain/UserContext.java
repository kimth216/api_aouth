package com.springboot.api.account.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * UserContext
 * Created by TaeHyeong Kim on 2020-07-23
**/

@Getter
@Setter
@NoArgsConstructor
public class UserContext {

  private String loginId;

  private String password;

  private String role;

  private String phone;

  private String username;

  private UserContext(final String loginId,
                      final String role,
                      final String username,
                      final String phone) {

    this.loginId = loginId;
    this.username = username;
    this.phone = phone;
    this.role = role;
    this.password = "FromC";
  }
  public static UserContext of(final String loginId,
                               final String role,
                               final String username,
                               final String phone) {
    return new UserContext(loginId, role, username, phone);
  }

}
