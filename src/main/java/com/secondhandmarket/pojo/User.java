package com.secondhandmarket.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author bruce
 * @since 2021-10-29
 */
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }
    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }
    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", phone=" + phone +
            ", username=" + username +
            ", password=" + password +
            ", qq=" + qq +
            ", createAt=" + createAt +
            ", goodsNum=" + goodsNum +
            ", power=" + power +
            ", lastLogin=" + lastLogin +
            ", status=" + status +
        "}";
    }
}
