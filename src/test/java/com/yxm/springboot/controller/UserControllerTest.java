package com.yxm.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.yxm.springboot.domain.User;
import com.yxm.springboot.service.UserService;
import com.yxm.springboot.web.UserController;
import net.minidev.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * @author yexinming
 * @date 2020/3/14
 **/
@RunWith(SpringRunner.class)
/**
 * 通过这个注解,你要测试的controller是谁?
 */
@WebMvcTest(UserController.class)
public class UserControllerTest {
    /**
     * 注入一个MockMvc,模拟对controller发起http请求
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * 模拟service组件
     */
    @MockBean
    private UserService userService;

    @Test
    public void testListUsers() throws Exception {
         //1.mock数据
        List<User> users = new ArrayList<User>();
        User user = new User();
        user.setId(1L);
        user.setAge(20);
        user.setName("测试");

        when(userService.listUsers()).thenReturn(users);

        //2.接口调用和断言
        mockMvc.perform(get("/user/"))
                .andExpect(content().json(JSONArray.toJSONString(users)));
    }

    /**
     * 测试用例：根据ID查询一个用户
     */
    @Test
    public void testGetUserById() throws Exception {

            Long userId = 1L;

            User user = new User();
            user.setId(userId);
            user.setName("测试用户");
            user.setAge(20);

            when(userService.getUserById(userId)).thenReturn(user);

            mockMvc.perform(get("/user/{id}", userId))
                    .andExpect(content().json(JSONObject.toJSONString(user)));

    }
    @Test
    public void testSaveUser() throws Exception {
        Long userId = 1L;

        User user = new User();
        user.setName("测试用户");
        user.setAge(20);
        when(userService.saveUser(user)).thenReturn(userId);

            mockMvc.perform(post("/user/").contentType("application/json").content(JSONObject.toJSONString(user)))
                    .andExpect(content().json("{'status': 'success', 'message': '新增用户ID为" + user.getId() + "'}"));
    }

    /**
     * 测试用例：修改用户
     */
    @Test
    public void testUpdateUser() throws Exception {
        Long userId = 1L;

        User user = new User();
        user.setId(userId);
        user.setName("测试用户");
        user.setAge(20);

        when(userService.updateUser(user)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put("/user/", userId).contentType("application/json").content(JSONObject.toJSONString(user)))
                .andExpect(content().string("success"));
    }

    /**
     * 测试用例：删除用户
     */
    @Test
    public void testRemoveUser() throws Exception {
        Long userId = 1L;

        when(userService.removeUser(userId)).thenReturn(true);


        mockMvc.perform(MockMvcRequestBuilders.delete("/user/{id}", userId))
            .andExpect(content().string("success"));

    }
}
