package com.selune.wechatordering.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 获取项目Url
 * @Author: Selune
 * @Date: 7/8/19 4:10 PM
 */

@Data
@Component
@ConfigurationProperties(prefix = "projectUrl")
public class ProjectUrlConfig {

    /**
     * 微信公众平台Url
     */
    public String wechatMpAuthorize;

    /**
     * 微信开放平台Url
     */
    public String wechatOpenAuthorize;

    /**
     * 点餐系统
     */
    public String sell;
}
