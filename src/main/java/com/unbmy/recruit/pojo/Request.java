package com.unbmy.recruit.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Unbmy
 */
@Data
@AllArgsConstructor
public class Request {

    private Long id;
    private Long userId;
    private String topic;
    private String host;
    private Integer people;
    @TableLogic
    private Integer isDeleted;

}
