package com.selune.wechatordering.service;

import com.selune.wechatordering.pojo.SellerInfo;

/**
 * 卖家端service接口
 * @Author: Selune
 * @Date: 7/8/19 11:21 AM
 */

public interface SellerService {

    /**
     * 通过openid查询卖家信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid) throws Exception;
}
