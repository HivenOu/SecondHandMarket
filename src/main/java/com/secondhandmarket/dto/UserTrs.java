package com.secondhandmarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTrs {

    /**
     * 登录使用的手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String pwd;

    /*角色
     **/
    private String role;
}
