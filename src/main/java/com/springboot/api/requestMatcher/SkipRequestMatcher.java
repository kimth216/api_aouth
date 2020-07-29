package com.springboot.api.requestMatcher;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

/**
 * SkipRequestMatcher
 * Created by TaeHyeong Kim on 2020-07-24
**/

// 하위 목록은 인증 무시 ( = 항상통과 )
@Getter
@AllArgsConstructor
public enum  SkipRequestMatcher {
  MEMBER_FINDALL(GET, "/member", "member find all"),
  MEMBER_CREATE(POST, "/member/create", "member create"),
  JOIN(POST, "/api/account", "Account Join"),
  LOGIN(POST, "/api/accounts/login", "Account Login"),
  REFRESH_TOKEN(GET, "/api/accounts/authorize", "Account Refresh Token"),

  SWAGGER_API_DOCS(GET, "/v2/api-docs", "Swagger default"),
  SWAGGER(GET, "/swagger/**", "Swagger"),
  SWAGGER_CONFIGURATION(GET, "/configuration/**", "Swagger default"),
  SWAGGER_RESOURCE(GET, "/swagger-resources/**", "Swagger default"),
  SWAGGER_WEBJARS(GET, "/webjars/**", "Swagger default"),
  SWAGGER_UI(GET, "/swagger-ui.html", "Swagger default"),
  ;
  private HttpMethod method;
  private String url;
  private String description;


//  public static void main(String[] args) {
//
//    List<RequestMatcher> res = getSkipRequestMatchers();
//    System.out.println(res.toString());
//
//  }


  public static List<RequestMatcher> getSkipRequestMatchers() {

    List<RequestMatcher> antPathRequestMatchers = new ArrayList<>();
    for (SkipRequestMatcher value : SkipRequestMatcher.values()) {
      antPathRequestMatchers.add(getSkipAntPathRequestMatcher(value));
    }
    return antPathRequestMatchers;
  }

  public static AntPathRequestMatcher getSkipAntPathRequestMatcher(final SkipRequestMatcher skipRequestMatcher) {
    return new AntPathRequestMatcher(skipRequestMatcher.getUrl(), (skipRequestMatcher.getMethod() != null) ? skipRequestMatcher.method.name() : null);
  }

}
