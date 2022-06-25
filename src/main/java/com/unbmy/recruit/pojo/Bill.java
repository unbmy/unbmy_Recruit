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
public class Bill {

    private Long id;
    private String topic;
    private String description;
    private Double cost;
    private Date billTime;
    private Date costTime;
    private Integer status;
    @TableLogic
    private Integer isDeleted;

    public Bill(String topic, String description, Double cost, Date billTime) {
        this.topic = topic;
        this.description = description;
        this.cost = cost;
        this.billTime = billTime;
    }

    public Bill(Long id, String topic, String description, Double cost) {
        this.id = id;
        this.topic = topic;
        this.description = description;
        this.cost = cost;
    }

    public String getRealBillTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(billTime);
    }

    public String getRealCostTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(costTime);
    }
}
