package com.secondhandmarket.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author bruce
 * @since 2021-11-01
 */
@Data
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品分类，外键
     */
    private Integer catelogId;

    /**
     * 用户外键
     */
    private Integer userId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 出售价格
     */
    private Float price;

    /**
     * 真实价格
     */
    private Float realPrice;

    /**
     * 发布时间
     */
    private String startTime;

    /**
     * 擦亮时间，按该时间进行查询，精确到时分秒
     */
    private String polishTime;

    /**
     * 下架时间
     */
    private String endTime;

    /**
     * 详细信息
     */
    private String describle;

    //被举报的数量
    private Integer informNum;

    /**
     * 状态 上架1 下架0
     */
    private Integer status;
    private Integer isdel;

    @TableField(exist = false) //告诉MyBatis Plus 这个字段是扩展字段 不是数据库中字段
    private String imageUrl;//扩展字段

    @Override
    public String toString() {
        return "Goods{" +
            "id=" + id +
            ", catelogId=" + catelogId +
            ", userId=" + userId +
            ", name=" + name +
            ", price=" + price +
            ", realPrice=" + realPrice +
            ", startTime=" + startTime +
            ", polishTime=" + polishTime +
            ", endTime=" + endTime +
            ", describle=" + describle +
            ", status=" + status +
        "}";
    }
}
