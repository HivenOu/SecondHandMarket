package com.secondhandmarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.secondhandmarket.pojo.Goods;
import com.secondhandmarket.pojo.Orders;
import com.secondhandmarket.mapper.OrdersMapper;
import com.secondhandmarket.pojo.Purse;
import com.secondhandmarket.service.IGoodsService;
import com.secondhandmarket.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhandmarket.service.IPurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bruce
 * @since 2021-11-04
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

    @Autowired
    IGoodsService goodsService;

    @Autowired
    IPurseService purseService;

    @Override
    @Transactional
    public void addOrder(Orders orders, Purse p) {
        //新增订单
        this.save(orders);
        //删除商品
        Goods goods = goodsService.getById(orders.getGoodsId());
        goods.setIsdel(1);
        goodsService.updateById(goods);
        //购买人-余额减少
        p.setBalance(p.getBalance()-orders.getOrderPrice());
        purseService.updateById(p);
        //卖家钱增加
        Integer userId = goods.getUserId();
        Purse sellerPurse = purseService.getOne(new QueryWrapper<Purse>().eq("user_id", userId));
        sellerPurse.setBalance(sellerPurse.getBalance()+orders.getOrderPrice());
        purseService.updateById(sellerPurse);
    }
}
