package com.yxm.springboot.web;

import com.yxm.springboot.domain.User;
import com.yxm.springboot.service.UserService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理模块的控制器组件
 *
 * 先初步介绍一下，什么是RESTful风格的接口，其实这个是一种风格和思想
 * 就是说他认为系统里的各种东西都是资源，暴露出去的接口，都是对资源的一种操作
 * 所以在请求URL里面，按照一种风格标志出来你要操作的是哪个资源
 * 然后通过HTTP method来定义你要对这个资源执行什么样的操作呢？
 *
 * @author yexinming
 * @date 2020/3/12
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     * @return 用户信息
     * 这个@GetMapping注解表示的就是，这个接口仅仅接收GET类型的http请求
     */
    @GetMapping("/")
    public List<User> listUsers() {
        return userService.listUsers();
    }

    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户信息
     *
     * {id}，就是通过占位符的方式，可以让我们提取请求URL中的参数
     *
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }
    /**
     * 新增用户
     * @param user 用户信息
     */
    @PostMapping("/")
    public String saveUser(@RequestBody @Validated({User.Save.class}) User user,
                         BindingResult bindingResult) {
        //1.校验逻辑:如果校验失败,则将检验失败信息返回
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            FieldError error = (FieldError)allErrors.get(0);
            String message = error.getObjectName() + ","
                    + error.getField() + ","
                    + error.getDefaultMessage();
            return "{'status': 'error', 'message': '" + message +"'}";
        }

        //2.业务逻辑
        userService.saveUser(user);
        return "success";
    }
    /**
     * 修改用户
     * @param user
     */
    @PutMapping("/")
    public void updateUser(@RequestBody User user) {
        System.out.println(user);
        userService.updateUser(user);
    }

    /**
     * 删除用户
     * @param id
     */
    @Delete("/{id}")
    public void removeUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
    }
}
