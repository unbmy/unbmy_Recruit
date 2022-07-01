package com.unbmy.recruit.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Unbmy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Maintenance {

    private Long id;
    private String topic;
    private String place;
    private String description;
    private String photo;
    private Date time;
    private Integer status;
    @TableLogic
    private Integer isDeleted;

    public Maintenance(String topic, String place, String description, String photo) {
        this.topic = topic;
        this.place = place;
        this.description = description;
        this.photo = photo;
        time = new Date();
    }

    public Maintenance(Long id, String topic, String place, String description) {
        this.id = id;
        this.topic = topic;
        this.place = place;
        this.description = description;
    }

    public String getRealTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(time);
    }

    public String getRealPhoto(){
        return "/maintenance/" + photo;
    }
}
