package com.selune.wechatordering.aspect;

import com.selune.wechatordering.constant.CookieConstant;
import com.selune.wechatordering.constant.RedisConstant;
import com.selune.wechatordering.exception.SellerAuthorizeException;
import com.selune.wechatordering.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * AOP 身份验证
 *
 * @Author: Selune
 * @Date: 7/8/19 10:16 PM
 */

//@Aspect // 微信扫码账号注册问题，已屏蔽扫码
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.selune.wechatordering.controller.Sell*.*(..))" +
            "&& " +
            "!execution(public * com.selune.wechatordering.controller.SellerUserController.*(..))")
    public void verify() {

    }

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 查询Cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (null == cookie) {
            log.warn("【登录校验】Cookie中查不到token");
            throw new SellerAuthorizeException();
        }

        // Redis 查询Cookie
        String tokenValue =
                redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX,
                        cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("【登录校验】Redis中查不到token");
            throw new SellerAuthorizeException();
        }
    }
}
