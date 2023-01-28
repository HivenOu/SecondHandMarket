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
 * @since 2021-11-02
 */
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户，外键
     */
    private Integer userId;
    /**
     * 评论人名字--扩展字段  表述数据库中没有这个字段
     */
    @TableField(exist = false)
    private String userName;

    /**
     * 商品，外键
     */
    private Integer goodsId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private String createAt;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Comments{" +
            "id=" + id +
            ", userId=" + userId +
            ", goodsId=" + goodsId +
            ", content=" + content +
            ", createAt=" + createAt +
        "}";
    }
}
