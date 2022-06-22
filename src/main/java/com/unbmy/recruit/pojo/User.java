package com.unbmy.recruit.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User implements Account{

    private Long id;
    private String username;
    private String password;

    @TableLogic
    Integer isDeleted;

    public User(String username, String password) {
    }
}
