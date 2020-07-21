package com.springboot.api.member;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

/**
 * Created by kth on 2020-07-21
 */
public class MemberDto {

  @Getter
  @Setter
  public static class CreateMember {

    @NotNull
    private String id;

    @NotNull
    private String name;

  }
}
