package com.springboot.api.account.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Role
 * Created by TaeHyeong Kim on 2020-07-23
**/

@Getter
@AllArgsConstructor
public enum Role {
  ADMIN("ROLE_ADMIN", "관리자"),
  USER("ROLE_USER", "사용자")
  ;

  private String roleName;
  private String description;

  public boolean isCorrectName(String name) {
    return name.equalsIgnoreCase(this.roleName);
  }

  public static Role getRoleByName(String roleName) {
    return Arrays.stream(Role.values())
        .filter(value -> value.isCorrectName(roleName))
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException("검색된 권한이 없습니다."));
  }

  public static Role getRoleNullableByName(String roleName) {
    return Arrays.stream(Role.values())
        .filter(value -> value.isCorrectName(roleName))
        .findFirst()
        .orElse(null);
  }

  public static boolean isInfluencer(Role role) {
    return USER.equals(role);
  }

}
