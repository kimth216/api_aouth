package com.springboot.api.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.oauth.security.TokenDto;
//import com.oauth.security.UserContext;
//import com.oauth.security.account.domain.Account;
//import com.oauth.security.account.domain.AccountContext;
//import com.oauth.security.jwt.JwtFactory;
//import com.oauth.security.token.AccountPostAuthorizationToken;
import com.springboot.api.TokenDto;
import com.springboot.api.account.domain.Account;
import com.springboot.api.context.AccountContext;
import com.springboot.api.account.domain.UserContext;
import com.springboot.api.jwt.JwtFactory;
import com.springboot.api.token.AccountPostAuthorizationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AccountJwtAuthenticationSuccessHandler
 * Created by TaeHyeong Kim on 2020-07-23
**/
@Component("accountJwtAuthenticationSuccessHandler")
@RequiredArgsConstructor
public class AccountJwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
  private final JwtFactory jwtFactory;

  private final ObjectMapper objectMapper;

  @Override
  public void onAuthenticationSuccess(final HttpServletRequest request,
                                      final HttpServletResponse response,
                                      final Authentication authentication) throws IOException, ServletException {
    AccountContext context = ((AccountPostAuthorizationToken) authentication).getAccountContext();
    Account account = context.getAccount();
    UserContext userContext = UserContext.of(
        account.getLoginId(),
        account.getRole().getRoleName(),
        account.getUsername(),
        account.getPhone()
    );
    String accessTokenString = jwtFactory.generateToken(userContext);
    String accountRefreshTokenString = jwtFactory.generateRefreshToken(userContext);

    processResponse(response, TokenDto.of(accessTokenString, accountRefreshTokenString));
  }

  private void processResponse(HttpServletResponse res, TokenDto tokenDto) throws IOException {
    res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    res.setStatus(HttpStatus.OK.value());
    res.getWriter().write(objectMapper.writeValueAsString(tokenDto));
  }
}
