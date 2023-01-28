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
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单表id
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
     * 订单编号
     */
    private Long orderNum;

    /**
     * 订单价格
     */
    private Float orderPrice;

    /**
     * 订单状态 1待发货 2待收货 3已完成
     */
    private Integer orderState;

    private String orderInformation;

    /**
     * 下单时间
     */
    private String orderDate;

    @TableField(exist = false)
    private Goods goods;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
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
    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }
    public Float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Float orderPrice) {
        this.orderPrice = orderPrice;
    }
    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }
    public String getOrderInformation() {
        return orderInformation;
    }

    public void setOrderInformation(String orderInformation) {
        this.orderInformation = orderInformation;
    }
    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Orders{" +
            "id=" + id +
            ", userId=" + userId +
            ", goodsId=" + goodsId +
            ", orderNum=" + orderNum +
            ", orderPrice=" + orderPrice +
            ", orderState=" + orderState +
            ", orderInformation=" + orderInformation +
            ", orderDate=" + orderDate +
        "}";
    }
}
