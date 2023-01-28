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
 * @since 2021-11-03
 */
public class Purse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 钱包id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    @TableField(exist = false)
    private String userName;

    /**
     * 总钱数
     */
    private Float balance;

    /**
     * 充值钱数
     */
    private Float recharge;

    /**
     * 提现钱数
     */
    private Float withdrawals;

    /**
     * 状态 0未审核   已审核（1不通过 2通过）
     */
    private Integer state;


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
    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }
    public Float getRecharge() {
        return recharge;
    }

    public void setRecharge(Float recharge) {
        this.recharge = recharge;
    }
    public Float getWithdrawals() {
        return withdrawals;
    }

    public void setWithdrawals(Float withdrawals) {
        this.withdrawals = withdrawals;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Purse{" +
            "id=" + id +
            ", userId=" + userId +
            ", balance=" + balance +
            ", recharge=" + recharge +
            ", withdrawals=" + withdrawals +
            ", state=" + state +
        "}";
    }
}
