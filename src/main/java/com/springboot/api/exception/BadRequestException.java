package com.springboot.api.exception;

import com.springboot.api.util.MsgSourceUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Munsu Seo on 2019-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BadRequestException extends RuntimeException {

  public BadRequestException(String msg) {
    super(msg);
  }

  public BadRequestException() {
    super(MsgSourceUtil.getMsg("common.bad.request"));
  }

  public static BadRequestException of(final String code) {
    return new BadRequestException(MsgSourceUtil.getMsg(code));
  }

  public static BadRequestException of(final String code,
                                       final Object object) {
    return new BadRequestException(MsgSourceUtil.getMsg(code, object));
  }

  public static BadRequestException of(String msg, Object... objects) {
    return new BadRequestException(MsgSourceUtil.getMsg(msg, objects));
  }

}
