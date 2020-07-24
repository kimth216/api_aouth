package com.springboot.api.account.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Account 
 * Created by TaeHyeong Kim on 2020-07-24
**/

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {

  private Account(final String loginId,
                  final String password,
                  final Role role,
                  final String username) {
    this.loginId = loginId;
    this.password = password;
    this.role = role;
    this.username = username;
  }

  public static Account of(final String loginId,
                           final String password,
                           final Role role,
                           final String username) {
    return new Account(loginId, password, role, username);
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long accountId;

  @Column(unique = true)
  private String loginId;

  private String email;

  private String password;

  private String username;

  private String phone;

  @Enumerated(EnumType.STRING)
  private Role role = Role.USER;

  private boolean enable;

  private boolean locked = false;

  private int loginFailCount;



}
