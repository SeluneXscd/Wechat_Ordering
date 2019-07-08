package com.selune.wechatordering.controller;

import com.selune.wechatordering.config.ProjectUrlConfig;
import com.selune.wechatordering.constant.CookieConstant;
import com.selune.wechatordering.constant.RedisConstant;
import com.selune.wechatordering.enums.ResultEnum;
import com.selune.wechatordering.pojo.SellerInfo;
import com.selune.wechatordering.service.SellerService;
import com.selune.wechatordering.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 卖家用户
 *
 * @Author: Selune
 * @Date: 7/8/19 4:34 PM
 */

@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String, Object> map) throws Exception {

        // 1. openid 和数据库相匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (null == sellerInfo) {
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url", "/sell/seller");
            return new ModelAndView("common/error", map);
        }

        // 2. 设置token 至 redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;

        redisTemplate.opsForValue().set(
                String.format(RedisConstant.TOKEN_PREFIX, token), // token key
                openid, // token value
                expire, // 过期时间,一定要设置，不然会占用redis资源
                TimeUnit.SECONDS // 单位秒
        );

        // 3. 设置token 至 cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

        // 地址跳转最好用绝对地址
        return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/sell/seller/order" +
                "/list");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletResponse response,
                       HttpServletRequest request,
                       Map<String, Object> map) {

        // 1. 从Cookie中查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (null != cookie) {
            // 2. 清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

            // 3. 清除Cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }

        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }
}
