package com.secondhandmarket.service;

import com.secondhandmarket.pojo.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.secondhandmarket.pojo.Purse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bruce
 * @since 2021-11-04
 */
public interface IOrdersService extends IService<Orders> {


    void addOrder(Orders orders, Purse p);
}
