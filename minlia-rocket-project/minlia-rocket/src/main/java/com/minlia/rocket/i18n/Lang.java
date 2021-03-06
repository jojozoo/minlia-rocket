package com.minlia.rocket.i18n;

import com.minlia.rocket.context.ContextHolder;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

/**
 * 多国语言译文获取
 *
 * @author will
 */
@Slf4j
public class Lang {

  public static String get(String key) {
    return get(key, new Object[]{}, LocaleContextHolder.getLocale());
  }

  public static String get(String key, Object[] arguments) {
    return get(key, arguments, LocaleContextHolder.getLocale());
  }

  public static String get(String key, Object[] arguments, Locale locale) {

    String result = null;
    try {
      ApplicationContext context = ContextHolder.getContext();
      if (null != context) {

        result=context.getMessage(key,arguments,result,locale);

        if(StringUtils.isEmpty(result)) {
          if (null == locale) {
            LocaleResolver localeResolver = context.getBean(LocaleResolver.class);
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
            locale = localeResolver.resolveLocale(request);
          }

          MessageSource messageSource = context.getBean(MessageSource.class);
          log.debug("MessageSource {}",messageSource);
          result = messageSource.getMessage(key, arguments, result, locale);
        }
      }else {
        log.error("{}","No ApplicationContext Found");
      }
    } catch (org.springframework.context.NoSuchMessageException e) {
      log.warn("No translated message found for key: {}", key);
    }
    return result;
  }


}
