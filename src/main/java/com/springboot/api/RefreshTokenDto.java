package com.springboot.api;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

/**
 * RefreshTokenDto 
 * Created by TaeHyeong Kim on 2020-07-25
**/
@Data
@NoArgsConstructor
public class RefreshTokenDto {

  private RefreshTokenDto(final String grantType,
                          final String accessToken,
                          final String refreshToken) {
    this.grantType = grantType;
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }

  public static RefreshTokenDto of(final HttpServletRequest req) {
    return new RefreshTokenDto(req.getParameter("grant_type"),
        req.getParameter("access_token"),
        req.getParameter("refresh_token"));
  }

  private String grantType;

  private String accessToken;

  private String refreshToken;

}
