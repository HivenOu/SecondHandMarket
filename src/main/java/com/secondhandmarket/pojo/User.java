package com.secondhandmarket.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author bruce
 * @since 2021-10-29
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登录使用的手机号
     */
    private String phone;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
    /*角色
    **/
    private String role;
    /*学生号*/
    private String studentNo;
    /*学院*/
    private String college;

    /**
     * 即时通讯
     */
    @TableField("QQ")
    private String qq;

    /**
     * 创建时间
     */
    private String createAt;

    /**
     * 发布过的物品数量
     */
    private Integer goodsNum;

    /**
     * 信用分，普通用户默认为100
     */
    private Integer power;

    /**
     * 最近一次登陆时间
     */
    private String lastLogin;

    /**
     * 账号是否冻结，默认0未冻结
     */
    private Integer status;

    /**
     * 是否删除
     */
    private Integer isdel;

}
