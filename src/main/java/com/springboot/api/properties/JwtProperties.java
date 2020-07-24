package com.springboot.api.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * JwtProperties 
 * Created by TaeHyeong Kim on 2020-07-24
**/
@Component
@PropertySource("classpath:jwt/jwt.properties")
@Data
public class JwtProperties {

  @Value("${jwt.expire.minutes}")
  private Long expireMinutes;

  @Value("${jwt.refresh.expire.minutes}")
  private Long refreshExpireMinutes;

}
