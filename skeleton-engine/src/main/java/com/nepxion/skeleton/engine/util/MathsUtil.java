package com.nepxion.skeleton.engine.util;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import org.apache.commons.lang3.StringUtils;

public class MathsUtil {
    private static final char ASTERISK = '*';

    public static Long calculate(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }

        long result = 1;
        try {
            String[] array = StringUtils.split(value, ASTERISK);
            for (String data : array) {
                result *= Long.parseLong(data.trim());
            }
        } catch (NumberFormatException e) {
            return null;
        }

        return result;
    }
}