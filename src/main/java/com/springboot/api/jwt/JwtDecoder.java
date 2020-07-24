package com.springboot.api.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.springboot.api.account.domain.UserContext;
import com.springboot.api.exception.InvalidJwtException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Munsu Seo on 2019-05-07
 */
@Component
public class JwtDecoder {

  public UserContext decodeJwt(final String token) {

    DecodedJWT decodedJWT = isValidToken(token).orElseThrow(() -> new InvalidJwtException("유효한 토큰이 아닙니다."));
    String loginId = decodedJWT.getClaim("LOGIN_ID").asString();
    String role = decodedJWT.getClaim("USER_ROLE").asString();
    String username = decodedJWT.getClaim("USER_NAME").asString();
    String phone = decodedJWT.getClaim("USER_PHONE").asString();
    String socialProvider = decodedJWT.getClaim("SOCIAL_PROVIDER").asString();

    return UserContext.of(loginId, role, username, phone);
  }

  private Optional<DecodedJWT> isValidToken(final String token) {

    Algorithm algorithm = Algorithm.HMAC256("CSYlsdpl6DMByUO7Xu96IpkzGnTEngSTMd7JPgtJJtcfNa3FxKKyHuSwKdoMFyzU");
    JWTVerifier verifier = JWT.require(algorithm).build();
    DecodedJWT decodedJWT = verifier.verify(token);

    return Optional.of(decodedJWT);
  }

}
