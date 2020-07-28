package com.springboot.api.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * EnvironmentProperties
 * Created by TaeHyeong Kim on 2020-07-28
**/

@Component
@Getter
@Setter
public class EnvironmentProperties {
  @Value("${spring.profiles.active}")
  private String activeProfile;
}
