package com.selune.wechatordering.constant;

/**
 * @Author: Selune
 * @Date: 7/8/19 8:55 PM
 */

public interface RedisConstant {

    /**
     * Token前缀
     */
    String TOKEN_PREFIX = "token_%s";

    /**
     * 过期时间2小时
     */
    Integer EXPIRE = 7200;
}
