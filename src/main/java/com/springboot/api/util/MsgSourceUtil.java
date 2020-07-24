package com.springboot.api.util;

import com.springboot.api.provider.BeanProvider;
import com.springboot.api.provider.MessageSourceProvider;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;

import java.util.Locale;

/**
 * Created by Munsu Seo on 2019-05-07
 */
public class MsgSourceUtil {

  private static MessageSource getDefaultMsgSource() {
    return BeanProvider.getBean(MessageSource.class);
  }

  public static String getMsg(final MessageSourceProvider messageSourceProvider) {
//    return getDefaultMsgSource().getMessage(messageSourceProvider, LocaleContextHolder.getLocale());
    return getDefaultMsgSource().getMessage(messageSourceProvider, Locale.KOREA);
  }

  public static String getMsg(final String code) {
//    return getDefaultMsgSource().getMessage(MessageSourceProvider.of(code), LocaleContextHolder.getLocale());
    return getDefaultMsgSource().getMessage(MessageSourceProvider.of(code), Locale.KOREA);
  }

  public static String getMsg(final String code,
                              final Object object) {
//    return getDefaultMsgSource().getMessage(MessageSourceProvider.of(code, object), LocaleContextHolder.getLocale());
    return getDefaultMsgSource().getMessage(MessageSourceProvider.of(code, object), Locale.KOREA);
  }

  public static String getMsg(final String code,
                              final Object... objects) {
//    return getDefaultMsgSource().getMessage(MessageSourceProvider.of(code, objects), LocaleContextHolder.getLocale());
    return getDefaultMsgSource().getMessage(MessageSourceProvider.of(code, objects), Locale.KOREA);
  }

  public static String getMsg(final MessageSourceResolvable resolvable) {
//    return getDefaultMsgSource().getMessage(MessageSourceProvider.of(resolvable), LocaleContextHolder.getLocale());
    return getDefaultMsgSource().getMessage(MessageSourceProvider.of(resolvable), Locale.KOREA);
  }

  public static String getMsgByLocale(final Locale locale,
                                      final String code) {
    return getDefaultMsgSource().getMessage(MessageSourceProvider.of(code), locale);
  }

  public static String getMsgByLocale(final Locale locale,
                                      final String code,
                                      final Object object) {
    return getDefaultMsgSource().getMessage(MessageSourceProvider.of(code, object), locale);
  }

  public static String getMsgByLocale(final Locale locale,
                                      final String code,
                                      final Object... objects) {
    return getDefaultMsgSource().getMessage(MessageSourceProvider.of(code, objects), locale);
  }

  public static String getMsgByLocale(final Locale locale,
                                      final MessageSourceResolvable resolvable) {
    return getDefaultMsgSource().getMessage(MessageSourceProvider.of(resolvable), locale);
  }

}
