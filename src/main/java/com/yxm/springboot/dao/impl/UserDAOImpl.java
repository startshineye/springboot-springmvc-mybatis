package com.yxm.springboot.dao.impl;

import com.yxm.springboot.dao.UserDAO;
import com.yxm.springboot.domain.User;
import com.yxm.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @author yexinming
 * @date 2020/3/12
 **/
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private UserMapper mapper;

    @Override
    public List<User> listUsers() {
        return mapper.listUsers();
    }

    @Override
    public User getUserById(Long id) {
        return mapper.getUserById(id);
    }

    @Override
    public Long saveUser(User user) {
       mapper.saveUser(user);
       return user.getId();
    }

    @Override
    public Boolean updateUser(User user) {
         mapper.updateUser(user);
         return true;
    }

    @Override
    public Boolean removeUser(Long id) {
           mapper.removeUser(id);
           return true;
    }
}
