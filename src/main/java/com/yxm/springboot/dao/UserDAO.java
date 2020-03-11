package com.yxm.springboot.dao;

import com.yxm.springboot.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户管理模块DAO接口
 */
public interface UserDAO {
    /**
     * 查询所有用户
     * @return
     */
    List<User> listUsers();

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User getUserById(Long id);
    /**
     * 插入用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 修改用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除用户
     * @param id
     */
    void removeUser(Long id);
}
