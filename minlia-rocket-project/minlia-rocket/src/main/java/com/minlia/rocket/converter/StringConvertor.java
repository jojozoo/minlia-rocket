package com.minlia.rocket.converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.StringUtils;

/**
 * @author will
 */
public class StringConvertor {

  private static final Pattern CAMEL_CASE_PATTERN = Pattern.compile("([^A-Z-])([A-Z])");

  private static final Pattern SEPARATED_TO_CAMEL_CASE_PATTERN = Pattern
      .compile("[_\\-.]");

  /**
   * Name variations.
   */
  public enum Variation {

    NONE {
      @Override
      public String apply(String value) {
        return value;
      }

    },

    LOWERCASE {
      @Override
      public String apply(String value) {
        return value.isEmpty() ? value : value.toLowerCase();
      }

    },

    UPPERCASE {
      @Override
      public String apply(String value) {
        return value.isEmpty() ? value : value.toUpperCase();
      }

    };

    public abstract String apply(String value);

  }

  /**
   * Name manipulations.
   */
  public enum Manipulation {

    NONE {
      @Override
      public String apply(String value) {
        return value;
      }

    },

    HYPHEN_TO_UNDERSCORE {
      @Override
      public String apply(String value) {
        return value.indexOf('-') != -1 ? value.replace('-', '_') : value;
      }

    },

    UNDERSCORE_TO_PERIOD {
      @Override
      public String apply(String value) {
        return value.indexOf('_') != -1 ? value.replace('_', '.') : value;
      }

    },

    PERIOD_TO_UNDERSCORE {
      @Override
      public String apply(String value) {
        return value.indexOf('.') != -1 ? value.replace('.', '_') : value;
      }

    },

    CAMELCASE_TO_UNDERSCORE {
      @Override
      public String apply(String value) {
        if (value.isEmpty()) {
          return value;
        }
        Matcher matcher = CAMEL_CASE_PATTERN.matcher(value);
        if (!matcher.find()) {
          return value;
        }
        matcher = matcher.reset();
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
          matcher.appendReplacement(result, matcher.group(1) + '_'
              + StringUtils.uncapitalize(matcher.group(2)));
        }
        matcher.appendTail(result);
        return result.toString();
      }

    },

    CAMELCASE_TO_HYPHEN {
      @Override
      public String apply(String value) {
        if (value.isEmpty()) {
          return value;
        }
        Matcher matcher = CAMEL_CASE_PATTERN.matcher(value);
        if (!matcher.find()) {
          return value;
        }
        matcher = matcher.reset();
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
          matcher.appendReplacement(result, matcher.group(1) + '-'
              + StringUtils.uncapitalize(matcher.group(2)));
        }
        matcher.appendTail(result);
        return result.toString();
      }

    },

    SEPARATED_TO_CAMELCASE {
      @Override
      public String apply(String value) {
        return separatedToCamelCase(value, false);
      }

    },

    CASE_INSENSITIVE_SEPARATED_TO_CAMELCASE {
      @Override
      public String apply(String value) {
        return separatedToCamelCase(value, true);
      }

    };

    private static final char[] SUFFIXES = new char[]{'_', '-', '.'};

    public abstract String apply(String value);

    public static String separatedToCamelCase(String value,
        boolean caseInsensitive) {
      if (value.isEmpty()) {
        return value;
      }
      StringBuilder builder = new StringBuilder();
      for (String field : SEPARATED_TO_CAMEL_CASE_PATTERN.split(value)) {
        field = (caseInsensitive ? field.toLowerCase() : field);
        builder.append(
            builder.length() == 0 ? field : StringUtils.capitalize(field));
      }
      char lastChar = value.charAt(value.length() - 1);
      for (char suffix : SUFFIXES) {
        if (lastChar == suffix) {
          builder.append(suffix);
          break;
        }
      }
      return builder.toString();
    }

  }
}