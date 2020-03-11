package com.yxm.springboot.mapper;

import com.yxm.springboot.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author yexinming
 * @date 2020/3/12
 **/
@Mapper
public interface UserMapper {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    @Results({
            @Result(property = "id",column = "id",id=true),
            @Result(property = "name",column = "name"),
            @Result(property = "age",column = "age")
    })
    List<User> listUsers();

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    @Results({
            @Result(property = "id",column = "id",id=true),
            @Result(property = "name",column = "name"),
            @Result(property = "age",column = "age")
    })
    User getUserById(@Param("id") Long id);
    /**
     * 插入用户
     * @param user
     */
    @Insert("insert into user(name,age) values(#{name},#{age})")
    void saveUser(User user);

    /**
     * 修改用户
     * @param user
     */
    @Update("UPDATE user SET name=#{name}, age=#{age} WHERE id=#{id}")
    void updateUser(User user);

    /**
     * 删除用户
     * @param id
     */
    @Delete("delete from user where id = #{id}")
    void removeUser(@Param("id") Long id);

}
