package com.unbmy.recruit.vo;

import com.unbmy.recruit.pojo.Notice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Unbmy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeVo {

    private Integer current;
    private Integer size;
    private Long totalPage;
    private Long total;
    private List<Notice> noticeList;

}
