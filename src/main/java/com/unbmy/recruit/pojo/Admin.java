package com.unbmy.recruit.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Admin implements Account{

    private Long id;
    private String username;
    private String password;

    @TableLogic
    Integer isDeleted;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
