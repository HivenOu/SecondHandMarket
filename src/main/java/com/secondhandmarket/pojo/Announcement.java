package com.secondhandmarket.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("announcement")
@Data
public class Announcement {
    private String todayRecommend;

    private String violationInfo;

    private String almanac;
}
