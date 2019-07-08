package com.selune.wechatordering.service.impl;

import com.selune.wechatordering.pojo.SellerInfo;
import com.selune.wechatordering.repository.SellerInfoRepository;
import com.selune.wechatordering.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 卖家端ServiceImpl
 * @Author: Selune
 * @Date: 7/8/19 11:22 AM
 */

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) throws Exception {
        return sellerInfoRepository.findByOpenId(openid);
    }
}
