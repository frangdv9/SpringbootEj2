package com.davinci.SpringbootEj2.models.DTO;

import com.davinci.SpringbootEj2.models.User;

public class UserDTO {
    private String name, email;
    private Integer age;
    public UserDTO(){

    };
    public UserDTO(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.age = user.getAge();
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }
}
