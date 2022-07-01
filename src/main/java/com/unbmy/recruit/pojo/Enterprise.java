package com.unbmy.recruit.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Unbmy
 */
@Data
@AllArgsConstructor
public class Enterprise implements Account{

    private Long id;
    private String username;
    private String password;
    private String enterpriseName;
    private String address;
    private String phone;
    @TableLogic
    private Integer isDeleted;

    public Enterprise(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Enterprise(String username, String password, String enterpriseName, String address, String phone) {
        this.username = username;
        this.password = password;
        this.enterpriseName = enterpriseName;
        this.address = address;
        this.phone = phone;
    }
}
