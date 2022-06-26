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
public class Housing {

    private Long id;
    private String topic;
    private String host;
    private Integer people;
    private String image;
    private Double area;
    private Date buyDate;
    @TableLogic
    private Integer isDeleted;

    public Housing(Long id, String topic, String host, Integer people) {
        this.id = id;
        this.topic = topic;
        this.host = host;
        this.people = people;
    }

    public String getRealBuyTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(getBuyDate());
    }

    public String getRealImage(){
        return "\\upload\\" + getImage();
    }
}
