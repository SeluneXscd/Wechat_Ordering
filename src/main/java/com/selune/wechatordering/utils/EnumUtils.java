package com.selune.wechatordering.utils;

import com.selune.wechatordering.enums.CodeEnum;

/**
 * @Author: Selune
 * @Date: 7/5/19 2:27 PM
 */

public class EnumUtils {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
