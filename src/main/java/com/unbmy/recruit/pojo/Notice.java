package com.unbmy.recruit.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Unbmy
 */
@Data
@AllArgsConstructor
public class Notice {

    private Long id;
    private String topic;
    private String content;
    private Date time;
    @TableLogic
    private Integer isDeleted;

    public Notice(String topic, String content, Date time) {
        this.topic = topic;
        this.content = content;
        this.time = time;
    }

    public String getRealTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        return simpleDateFormat.format(time);
    }
}
