package com.selune.wechat_ordering.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 类目标
 * product_category
 * @author selune
 */
@Entity
@Data
@DynamicUpdate
public class ProductCategory {

	/** 类目id */
	@Id
	@GeneratedValue
	private Integer categoryId;

	/** 类目名称 */
	private String categoryName;

	/** 类目编号 */
	private Integer categoryType;

	public ProductCategory() {
	}

	public ProductCategory(String categoryName, Integer categoryType) {
		this.categoryName = categoryName;
		this.categoryType = categoryType;
	}
}
