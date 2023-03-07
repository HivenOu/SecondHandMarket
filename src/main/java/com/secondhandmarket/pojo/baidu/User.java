package com.secondhandmarket.pojo.baidu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{
    @JsonProperty("group_id")
    private String groupId;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("user_info")
    private String userInfo;
    private double score;
}