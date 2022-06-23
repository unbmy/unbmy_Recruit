package com.unbmy.recruit.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Unbmy
 */
@Data
@AllArgsConstructor
public class User implements Account{

    private Long id;
    private String username;
    private String password;
    private String email;
    @TableLogic
    Integer isDeleted;
    Integer isRegistered;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
