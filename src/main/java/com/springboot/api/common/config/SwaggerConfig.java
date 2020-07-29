package com.springboot.api.common.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * SwaggerConfig 
 * Created by TaeHyeong Kim on 2020-07-22
**/
@Configuration
@EnableSwagger2
@Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig extends WebMvcConfigurationSupport {

  @Bean public Docket swaggerSpringfoxDocket() {
    Docket docket = new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiEndPointsInfo())
        .pathMapping("/") .forCodeGeneration(true)
        .genericModelSubstitutes(ResponseEntity.class)
        .ignoredParameterTypes(java.sql.Date.class)
        .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
        .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
        .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
        .securityContexts(Lists.newArrayList(securityContext()))
        .securitySchemes(Lists.newArrayList(apiKey()))
        .useDefaultResponseMessages(false);
    docket = docket.select() .paths(PathSelectors.any()) .build();
    return docket;
  }

    private ApiInfo apiEndPointsInfo() {
    return new ApiInfoBuilder().title("Decide REST API")
        .description("This documents describes about decide version 7 REST API")
        .contact(new Contact("Kim Young Hoi", "kimyhcj.tistory.com", "kimyhcj@gmail.com"))
        .license("Apache 2.0") .version("v1") .build();
  }

  private ApiKey apiKey() {
    return new ApiKey("JWT", "HEADER_AUTH", "header");
  }

  private springfox.documentation.spi.service.contexts.SecurityContext securityContext() {
    return springfox.documentation.spi.service.contexts.SecurityContext
        .builder()
        .securityReferences(defaultAuth())
        .forPaths(PathSelectors.any())
        .build();
  }

  List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
  }

  @Override
  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

}
