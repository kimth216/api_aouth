package com.springboot.api.member;

import lombok.*;

import javax.persistence.*;

/**
 * MemberVo
 * Created by TaeHyoung Kim on 2020-07-21
**/
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="member")
public class MemberVo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long mbrNo;

  private String id;

  private String name;

  @Builder
  public MemberVo(String id, String name) {
    this.id = id;
    this.name = name;
  }

}


