package com.pingtaiproject.service.model;

import javax.validation.constraints.NotBlank;

public class UserModel {
    private Integer id;
    @NotBlank(message = "用户名不能为空")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
