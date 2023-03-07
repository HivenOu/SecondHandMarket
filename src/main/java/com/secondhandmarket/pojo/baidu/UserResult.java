package com.secondhandmarket.pojo.baidu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResult{
    @JsonProperty("face_token")
    private String faceToken;
    @JsonProperty("user_list")
    private List<User> userList;
}