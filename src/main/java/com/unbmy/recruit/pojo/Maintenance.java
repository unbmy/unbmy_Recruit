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
public class Maintenance {

    private Long id;
    private String place;
    private String description;
    private String photo;
    private Date time;
    private Integer status;
    @TableLogic
    private Integer isDeleted;

    public Maintenance(String place, String description, String photo) {
        this.place = place;
        this.description = description;
        this.photo = photo;
        time = new Date();
    }

    public String getRealTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(time);
    }
}
