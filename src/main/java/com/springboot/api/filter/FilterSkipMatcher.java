package com.springboot.api.filter;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * FilterSkipMatcher 
 * Created by TaeHyeong Kim on 2020-07-25
**/
public class FilterSkipMatcher implements RequestMatcher {

  private OrRequestMatcher orRequestMatcher;

  private RequestMatcher requestMatcher;

  public FilterSkipMatcher(final List<RequestMatcher> skipAntPathRequestMatchers,
                           final String processingPath) {

    this.orRequestMatcher = new OrRequestMatcher(skipAntPathRequestMatchers);
    this.requestMatcher = new AntPathRequestMatcher(processingPath);
  }

  @Override
  public boolean matches(HttpServletRequest req) {
    return !orRequestMatcher.matches(req) && requestMatcher.matches(req);
  }

}
