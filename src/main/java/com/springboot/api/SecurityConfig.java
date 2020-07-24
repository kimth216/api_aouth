package com.springboot.api;


import com.springboot.api.filter.AccountJwtRefreshAuthenticationFilter;
import com.springboot.api.filter.AccountLoginFilter;
import com.springboot.api.filter.FilterSkipMatcher;
import com.springboot.api.filter.JwtAuthenticationFilter;
import com.springboot.api.handler.AccountJwtAuthenticationSuccessHandler;
import com.springboot.api.handler.JwtAuthenticationFailureHandler;
import com.springboot.api.jwt.HeaderTokenExtractor;
import com.springboot.api.provider.AccountLoginAuthenticationProvider;
import com.springboot.api.provider.JwtAuthenticationProvider;
import com.springboot.api.requestMatcher.SkipRequestMatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityConfig
 * Created by TaeHyeong Kim on 2020-07-23
**/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final AccountJwtAuthenticationSuccessHandler accountJwtAuthenticationSuccessHandler;

  private final JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler;

  private final JwtAuthenticationProvider jwtAuthenticationProvider;

  private final AccountLoginAuthenticationProvider accountLoginAuthenticationProvider;

  private final HeaderTokenExtractor headerTokenExtractor;

  @Bean
  public AuthenticationManager getAuthenticationManager() throws Exception {
    return super.authenticationManagerBean();
  }

  private AccountLoginFilter accountLoginFilter() throws Exception {
    AccountLoginFilter filter = new AccountLoginFilter(
        SkipRequestMatcher.LOGIN.getUrl(),
        accountJwtAuthenticationSuccessHandler,
        jwtAuthenticationFailureHandler
    );
    filter.setAuthenticationManager(super.authenticationManagerBean());
    return filter;
  }

  private JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
    FilterSkipMatcher matcher = new FilterSkipMatcher(SkipRequestMatcher.getSkipRequestMatchers(), "/api/**");
    JwtAuthenticationFilter filter = new JwtAuthenticationFilter(matcher, jwtAuthenticationFailureHandler, headerTokenExtractor);
    filter.setAuthenticationManager(super.authenticationManagerBean());
    return filter;
  }

  private AccountJwtRefreshAuthenticationFilter accountJwtRefreshAuthenticationFilter() throws Exception {

    AccountJwtRefreshAuthenticationFilter filter = new AccountJwtRefreshAuthenticationFilter(
        SkipRequestMatcher.REFRESH_TOKEN.getUrl(),
        accountJwtAuthenticationSuccessHandler,
        jwtAuthenticationFailureHandler);
    filter.setAuthenticationManager(super.authenticationManagerBean());

    return filter;
  }



  @Override
  protected void configure(final AuthenticationManagerBuilder auth) {
    auth
        .authenticationProvider(this.accountLoginAuthenticationProvider)
        .authenticationProvider(this.jwtAuthenticationProvider)
    ;
  }

  public void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    httpSecurity.cors();
    httpSecurity.csrf().disable();
    httpSecurity.headers().frameOptions().disable();
    httpSecurity
                .addFilterBefore(accountLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(accountJwtRefreshAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
    ;
  }
}
