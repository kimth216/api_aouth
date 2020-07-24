package com.springboot.api.jwt;

import com.springboot.api.exception.InvalidJwtException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by Munsu Seo on 2019-05-07
 */
@Component
public class HeaderTokenExtractor {

  public String extract(final String header) {

    String headerToken = "Bearer ";

    if (StringUtils.isEmpty(header)) {
      throw new InvalidJwtException("올바른 토큰 정보가 아닙니다.");
    }

    int headerTokenLength = headerToken.length();
    int headerLength = header.length();

    if (StringUtils.isBlank(header) | header.length() < headerTokenLength) {
      throw new InvalidJwtException("올바른 토큰 정보가 아닙니다.");
    }

    return header.substring(headerTokenLength, headerLength);
  }

}
