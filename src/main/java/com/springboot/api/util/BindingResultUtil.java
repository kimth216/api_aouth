package com.springboot.api.util;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;

/**
 * BindingResultUtil 
 * Created by TaeHyeong Kim on 2020-07-24
**/
public class BindingResultUtil extends BindingResultUtils {

  public static void handleBindingResult(BindingResult bindingResult) throws BindException {
    if (bindingResult.hasErrors()) {
      throw new BindException(bindingResult);
    }
  }

}
