package com.secondhandmarket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @JsonProperty("group_id")
    private String groupId;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("user_info")
    private String userInfo;
    private Double score;
}