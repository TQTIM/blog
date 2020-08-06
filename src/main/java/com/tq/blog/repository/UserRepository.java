package com.tq.blog.repository;

import com.tq.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 编写dao接口处理数据库user表的相关操作。（接口）
 *  继承JpaRepository接口来完成对数据库的操作
 *   泛型是（实体类，主键）
 */
public interface UserRepository extends JpaRepository<User,Long> {
    //自带没有 就自定义一个查询用户名和密码方法，会自动进行数据库操作（语句要遵守规则）
    User findByUsernameAndPassword(String username, String password);//也可以随便写个方法并用@Query(value="sql语句")注解

}
