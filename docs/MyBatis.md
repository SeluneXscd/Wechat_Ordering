

# MyBatis

## 什么是 MyBatis？

MyBatis 是一款优秀的持久层框架，它支持定制化 SQL、存储过程以及高级映射。

MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。

MyBatis 可以使用简单的 XML 或注解来配置和映射原生类型、接口和 Java 的 POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。

## 使用MyBatis

### SpringBoot加入Dependency

```xml
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.0.1</version>
</dependency>
```

### 一、注解

#### @MapperScan()

  在SpringBoot的启动类中，加入注解@MapperScan，用于扫描Mapper接口。

  可以免除Mapper接口的注解。

  ```java
public interface UserMapper{

}

@SpringBootApplication
@MapperScan(basePackages = "com.test.domain.mapper") // 定义UserMapper接口所在的包
public class App {

}
  ```

#### @Insert

  用于定义Insert SQL。

  - 使用Map插入
  ```java
@Insert("insert into user(user_name, user_age) " +
        "values (" +
        "#{user_name, jdbcType=VARCHAR}," +
        "#{user_age, jdbcType=INTEGER}" +
        ")")
int insertByMap(Map<String, Object> map);

@Test
public void insertByMap() throws Exception {
    Map<String, Object> map = new HashMap<>();
    map.put("user_name", "小明");
    map.put("user_age", 21);
    int result = userMapper.insertByMap(map);
    Assert.assertEquals(1, result);
}
  ```

  - 使用Object类插入

  ```java
@Insert("insert into user(user_name, user_age) " +
        "values (" +
        "#{userName, jdbcType=VARCHAR}," +
        "#{userAge, jdbcType=INTEGER}" +
        ")")
int insertByObject(User user);

@Test
public void insertByMap() throws Exception {
    User user = new User();
    user.setUserName("小明");
    user.setUserAge(22);
    int result = userMapper.insertByObject(user);
    Assert.assertEquals(1, result);
}
  ```

#### @Select

@Select注解，需要加上@Results设置返回结果集

```java
@Select("select * from user where user_name = #{userName}")
@Results({ // 结果返回设置结果集
    	@Result(column = "user_id", property = "userId"),
        @Result(column = "user_name", property = "userName"),
        @Result(column = "user_password", property = "userPassword"),
})
User findByUserName(String userName);
```

#### @Update

官方规定，@Update注解，传递参数时，需要用到@Parma注解

```java
@Update("update user " +
		"set user_name = #{userName} " +
		"where user_id =  #{userId}")
int updateByCategoryType(@Param("userName") String userName,
						 @Param("userId") Integer userId);
```

直接通过传递对象更新

```java
@Update("update user " +
		"set user_name = #{userName} " +
		"where user_id =  #{userId}")
int updateByCategoryType(User user);
```

#### @Delete

```java
@Delete("delete from user where user_id = #{userId}")
int deleteByUserId(Integer userId);
```

#### 使用

习惯上，把mapper封装成dao，再在service中注入使用

### 二、xml文件

UserMapper.java

```java
User selectByUserId(Integer userId);
```

UserMapper.xml

```xml
<mapper namespace="com.domain.mapper.UserMapper">

    <resultMap id="BaseResultMap" type="com.domain.User">
        <id column="user_id" property="userId" jdbcType="INTEGER" />
        <id column="user_name" property="userName" jdbcType="VARCHAR" />
        <id column="user_age" property="userAge" jdbcType="INTEGER" />
    </resultMap>

    <select id="selectByUserId" resultMap="BaseResultMap" 			parameterType="integer">
        select user_id, user_name, user_age
        from user
        where user_id = #{userId}
    </select>
</mapper>
```

## JPA和MyBatis的选择

- JPA：不需要定义SQL语句
- MyBatis：可以定制SQL语句
- 建表使用SQL，不建议JPA自动建表
- 慎用@OneToMany和@ManyToOne