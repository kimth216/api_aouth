package com.springboot.api.exception;

/**
 * InvalidJwtException
 * Created by TaeHyeong Kim on 2020-07-24
**/
public class InvalidJwtException extends RuntimeException {

  public InvalidJwtException(final String msg) {
    super(msg);
  }

}
