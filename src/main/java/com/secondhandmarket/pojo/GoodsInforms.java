package com.secondhandmarket.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
     * 商品id
     */
    private Integer goodsId;

    /**
     * 举报编号
     */
    private Long informNo;


    private String informInformation;

    //举报时间
    private Date informTime;


    @TableField(exist = false)
    private Goods goods;

}
