package com.secondhandmarket.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("announcement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {
    private String todayRecommend;

    private String violationInfo;

    private String almanac;
}
