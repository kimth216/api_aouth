package com.springboot.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * TokenDto
 * Created by TaeHyeong Kim on 2020-07-23
**/
@Getter
public class TokenDto {
  private TokenDto(final String accessToken,
                   final String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }

  @JsonProperty(value = "access_token")
  private String accessToken;

  @JsonProperty(value = "refresh_token")
  private String refreshToken;

  public static TokenDto of(final String accessToken,
                            final String refreshToken){
    return new TokenDto(accessToken, refreshToken);

  }
}
