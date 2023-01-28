package com.secondhandmarket.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author bruce
 * @since 2021-11-04
 */
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统信息主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户，外键
     */
    private Integer userId;

    /**
     * 扩展对象
     */
    @TableField(exist = false)
    private User user;

    /**
     * 信息内容
     */
    private String context;

    /**
     * 推送信息时间
     */
    private String createAt;

    /**
     * 状态，0未读，1已读
     */
    private Integer status;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Notice{" +
            "id=" + id +
            ", userId=" + userId +
            ", context=" + context +
            ", createAt=" + createAt +
            ", status=" + status +
        "}";
    }
}
