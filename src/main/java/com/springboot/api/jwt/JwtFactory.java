package com.springboot.api.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.springboot.api.account.domain.UserContext;
import com.springboot.api.properties.JwtProperties;
import com.springboot.api.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * Created by Munsu Seo on 2019-05-07
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFactory {

  private final JwtProperties jwtProp;

  public String generateToken(final UserContext context) {
    return generateToken(context, SecurityUtil.getDateFromUtcZonedDateTime(jwtProp.getExpireMinutes()));
  }

  public String generateRefreshToken(final UserContext context) {
    return generateToken(context, SecurityUtil.getDateFromUtcZonedDateTime(jwtProp.getRefreshExpireMinutes()));
  }

  private String generateToken(final UserContext userContext,
                               final Date expire) {

    String token = null;
    try {
      token = JWT.create()
        .withIssuer("https://www.from-c.co.kr")
        .withIssuedAt(SecurityUtil.getDateFromUtcZonedDateTime())
        .withExpiresAt(expire)
        .withClaim("LOGIN_ID", userContext.getLoginId())
        .withClaim("USER_ROLE", userContext.getRole())
        .withClaim("USER_NAME", userContext.getUsername())
        .withClaim("USER_PHONE", !StringUtils.isEmpty(userContext.getPhone()) ? userContext.getPhone() : "")
        .sign(generateAlgorithm());
    } catch (Exception e) {
      log.error(e.getMessage());
    }

    return token;
  }

  private Algorithm generateAlgorithm() {
    return Algorithm.HMAC256("CSYlsdpl6DMByUO7Xu96IpkzGnTEngSTMd7JPgtJJtcfNa3FxKKyHuSwKdoMFyzU");
  }

}
