package com.selune.wechatordering.service.impl;

import com.selune.wechatordering.exception.WeChatOrderException;
import com.selune.wechatordering.service.SecKillService;
import com.selune.wechatordering.utils.KeyUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Selune
 * @Date: 7/9/19 6:23 PM
 */

@Service
public class SecKillServiceImpl implements SecKillService {

    /**
     * 活动，特价，限量100000份
     */
    static Map<String, Integer> products;//模拟商品信息表
    static Map<String, Integer> stock;//模拟库存表
    static Map<String, String> orders;//模拟下单成功用户表

    static {
        /**
         * 模拟多个表，商品信息表，库存表，秒杀成功订单表
         */
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("123456", 100000);
        stock.put("123456", 100000);
    }

    private String queryMap(String productId) {//模拟查询数据库
        return "秒杀活动，限量销售"
                + products.get(productId)
                + "份,还剩:" + stock.get(productId)
                + "份,该商品成功下单用户数:"
                + orders.size() + "人";
    }


    @Override
    public String querySecKillProductInfo(String productId) {
        return this.queryMap(productId);
    }

    @Override
    public synchronized void orderProductMockDiffUser(String productId) {
        // 1. 查询库存，为0则活动结束
        int stockNum = stock.get(productId);
        if (stockNum == 0) {
            throw new WeChatOrderException(100, "活动结束");
        } else {
            // 2. 下单（模拟不同用户的openid
            orders.put(KeyUtil.getUniqueKey(), productId);
            // 3, 减库存
            stockNum -= 1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stock.put(productId, stockNum);
        }

    }


}
