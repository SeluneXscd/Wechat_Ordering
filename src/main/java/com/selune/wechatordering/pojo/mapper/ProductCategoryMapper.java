package com.selune.wechatordering.pojo.mapper;

import com.selune.wechatordering.pojo.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: Selune
 * @Date: 7/9/19 10:45 AM
 */

public interface ProductCategoryMapper {

    /**
     * 通过Map插入
     * @param map
     * @return
     */
    @Insert("insert into product_category(category_name, category_type) " +
            "values (" +
            "#{category_name, jdbcType=VARCHAR}," +
            "#{category_type, jdbcType=INTEGER}" +
            ")")
    int insertByMap(Map<String, Object> map);

    /**
     * 通过ProductCategory对象插入
     * @param productCategory
     * @return
     */
    @Insert("insert into product_category(category_name, category_type) " +
            "values (" +
            "#{categoryName, jdbcType=VARCHAR}," +
            "#{categoryType, jdbcType=INTEGER}" +
            ")")
    int insertByObject(ProductCategory productCategory);

    /**
     * 根据categoryType查询Category
     * @param categoryType
     * @return
     */
    @Select("select * from product_category where category_type = #{categoryType}")
    @Results({ // 结果返回设置结果集
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    ProductCategory findByCategoryType(Integer categoryType);

    /**
     * 根据categoryName查询Category
     * @param categoryName
     * @return
     */
    @Select("select * from product_category where category_name = #{categoryName}")
    @Results({ // 结果返回设置结果集
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<ProductCategory> findByCategoryName(String categoryName);

    /**
     * 更新，根据参数
     * @param categoryName
     * @param categoryType
     * @return
     */
    @Update("update product_category " +
            "set category_name = #{categoryName} " +
            "where category_type =  #{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName, // 官方规定需要使用注解更新
                             @Param("categoryType") Integer categoryType);

    /**
     * 根据传递对象更新
     * @param productCategory
     * @return
     */
    @Update("update product_category " +
            "set category_name = #{categoryName} " +
            "where category_type =  #{categoryType}")
    int updateByObject(ProductCategory productCategory);

    /**
     * 根据categoryType删除
     * @param categoryType
     * @return
     */
    @Delete("delete from product_category where category_type = #{categoryType}")
    int deleteByCategoryType(Integer categoryType);
}
