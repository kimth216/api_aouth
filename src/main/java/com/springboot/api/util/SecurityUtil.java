package com.springboot.api.util;

import com.springboot.api.account.domain.Account;
import com.springboot.api.context.AccountContext;
import com.springboot.api.account.domain.AccountDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * SecurityUtil 
 * Created by TaeHyeong Kim on 2020-07-24
**/
@Slf4j
public class SecurityUtil {

  private static Authentication getTokenPayload() {
    return Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication());
  }

  private static Account getAuthenticationToAccount(Authentication authentication) {
    return ((AccountContext) authentication.getPrincipal()).getAccount();
  }

 

  public static AccountDto.AccountInfo getAccountInfo() {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(getAuthenticationToAccount(getTokenPayload()), AccountDto.AccountInfo.class);
  }

  

  private static ZonedDateTime getUtcZonedDateTime() {
    return ZonedDateTime.now(ZoneId.of("UTC"));
  }

  public static Date getDateFromUtcZonedDateTime(final Long minutes) {
    return Date.from(getUtcZonedDateTime().plusMinutes(minutes).toInstant());
  }

  public static Date getDateFromUtcZonedDateTime() {
    return getDateFromUtcZonedDateTime(0L);
  }

}
