package com.github.neatlife.jframework.fundation.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author suxiaolin
 */
public class CaseUtil {

    private static Pattern p = Pattern.compile("_(.)");

    public static String camelCase(String origin) {
        Matcher matcher = p.matcher(origin);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }

        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String snakeCase(String origin) {
        return origin.replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase();
    }

}
