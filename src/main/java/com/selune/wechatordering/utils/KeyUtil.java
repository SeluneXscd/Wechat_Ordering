package com.selune.wechatordering.utils;

import java.util.Random;

/**
 * @Author: Selune
 * @Date: 5/14/19 7:01 PM
 */

public class KeyUtil {

    /**
     * 生成唯一主键
     * 格式: 时间+随机数
     * @return 唯一主键
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();

        long time = System.currentTimeMillis();

        Integer number = random.nextInt(900000) + 100000;

        return String.valueOf(time + number);
    }
}
