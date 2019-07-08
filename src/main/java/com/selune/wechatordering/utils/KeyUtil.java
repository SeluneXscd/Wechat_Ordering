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
    public static synchronized String getUniqueKey() {
        Random random = new Random();

        long time = System.currentTimeMillis();

        int number = random.nextInt(900000) + 100000;

        return String.valueOf(time + number);
    }

    public static synchronized String getProductId() {
        Random random = new Random();

        int number = random.nextInt(900) + 100;

        return String.valueOf("100" + number);
    }

    public static synchronized String getSellerId() {
        Random random = new Random();
        int number = random.nextInt(90) + 10;
        return String.valueOf(100 + number);
    }
}
