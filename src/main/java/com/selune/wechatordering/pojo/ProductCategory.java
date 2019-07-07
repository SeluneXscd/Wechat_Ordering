package com.selune.wechatordering.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

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

	private Date createTime;

	private Date updateTime;

	public ProductCategory() {
	}

	public ProductCategory(String categoryName, Integer categoryType) {
		this.categoryName = categoryName;
		this.categoryType = categoryType;
	}
}
