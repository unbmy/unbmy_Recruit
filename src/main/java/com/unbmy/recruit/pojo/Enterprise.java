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

    @TableLogic
    private Integer isDeleted;

    public Enterprise(String username, String password) {
    }
}
