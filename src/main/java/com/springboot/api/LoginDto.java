package com.springboot.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


/**
 * LoginDto
 * Created by TaeHyeong Kim on 2020-07-23
**/
@Data
public class LoginDto {

  @JsonProperty(value = "loginId")
  private String loginId;

  @JsonProperty(value = "password")
  private String password;

}
