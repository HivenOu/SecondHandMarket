package com.secondhandmarket.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author bruce
 * @since 2021-11-04
 */
@TableName("goods_informs")
@Data
public class GoodsInforms implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 举报商品id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 举报用户id
     */
    private Integer userId;

    /**
     * 举报用户名
     */
    @TableField(exist = false)
    private String userName;


    /**
     * 商品id
     */
    private Integer goodsId;

    @TableField(exist = false)
    private String goodsName;

    /**
     * 举报编号
     */
    private Long informNo;


    private String informInformation;

    //举报时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date informTime;


}
