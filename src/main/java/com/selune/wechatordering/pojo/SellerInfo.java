package com.selune.wechatordering.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: Selune
 * @Date: 7/8/19 11:01 AM
 */

@Entity
@Table(name = "seller_info")
@Data
public class SellerInfo {

    @Id
    private String sellerId;

    private String username;

    private String password;

    @Column(name = "openid")
    private String openId;
}
