package com.yxm.springboot.service.impl;
import com.yxm.springboot.dao.UserDAO;
import com.yxm.springboot.domain.User;
import com.yxm.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * 用户管理模块的service组件实现类
 * @author yexinming
 * @date 2020/3/12
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO dao;

    @Override
    public List<User> listUsers() {
        return dao.listUsers();
    }

    @Override
    public User getUserById(Long id) {
        return dao.getUserById(id);
    }

    @Override
    public Long saveUser(User user) {
       return dao.saveUser(user);
    }

    @Override
    public Boolean updateUser(User user) {
        return dao.updateUser(user);
    }

    @Override
    public Boolean removeUser(Long id) {
       return  dao.removeUser(id);
    }
}
