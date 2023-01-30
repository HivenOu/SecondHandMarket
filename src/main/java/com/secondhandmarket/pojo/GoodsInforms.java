package com.secondhandmarket.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author bruce
 * @since 2021-11-04
 */
@Data
public class GoodsInforms implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 举报商品id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;


    private Integer sellerId;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 举报编号
     */
    private Long InformNum;


    private String InformInformation;


    @TableField(exist = false)
    private Goods goods;

}
