package com.secondhandmarket.pojo.baidu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResp {
    @JsonProperty("error_code")
    private int errorCode;
    @JsonProperty("error_msg")
    private String errorMsg;
    @JsonProperty("log_id")
    private int logId;
    private int timestamp;
    private int cached;
    private UserResult result;
}
