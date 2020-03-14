package com.yxm.springboot.domain;

import com.yxm.springboot.validate.Age;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

/**
 * @author yexinming
 * @date 2020/3/12
 **/
public class User {
    //定义校验接口组
    public interface Update{};
    public interface Save{};

    /**
     * 这就是一个典型的group分组
     * 更新操作的时候，要求id必须不为空
     * 新增操作的时候，要求id必须为空
     */
    @NotNull(groups = {Update.class})
    @Null(groups = {Save.class})
    private Long id;

    /**
     * 新增,修改时候牵涉到此参数,所以需要校验
     */
    @Size(min = 3,max = 20,groups = {Save.class,Update.class})
    private String name;

/*    @Range(min = 1,max = 120,groups = {Save.class,Update.class})*/
    @Age(min = 1,max = 120,groups = {Save.class,Update.class})
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
    }

}
