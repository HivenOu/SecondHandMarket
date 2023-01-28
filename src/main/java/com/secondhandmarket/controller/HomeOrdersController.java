package com.secondhandmarket.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.secondhandmarket.pojo.*;
import com.secondhandmarket.service.IGoodsService;
import com.secondhandmarket.service.IImageService;
import com.secondhandmarket.service.IOrdersService;
import com.secondhandmarket.service.IPurseService;
import com.secondhandmarket.utils.DateUtils;
import com.secondhandmarket.utils.ResultCode;
import com.secondhandmarket.utils.ResultCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bruce
 * @since 2021-11-04
 */
@Controller
@RequestMapping("/orders")
public class HomeOrdersController {

    @Autowired
    IGoodsService goodsService;

    @Autowired
    IImageService imageService;

    @Autowired
    IOrdersService ordersService;

    @Autowired
    IPurseService purseService;

    /**
     * 跳转支付页面
     * @return
     */
    @GetMapping("/toPay/{goodsId}")
    public String toPay(@PathVariable("goodsId") Integer goodsId, Model model){
        Goods goods = goodsService.getById(goodsId);
        List<Image> images = imageService.list(new QueryWrapper<Image>().eq("goods_id", goodsId));
        if(images.size()>0){
            String imageUrl = images.get(0).getImgUrl();
            goods.setImageUrl(imageUrl);
        }
        model.addAttribute("goods",goods);
        return "/user/pay";
    }

    /**
     * 新增订单到数据库
     * @return
     */
    @PostMapping("/createOrder")
    @ResponseBody
    public ResultCommon createOrder(Orders orders, HttpSession session){
        try {
            //钱包--钱包同步
            User loginUser = (User) session.getAttribute("loginUser");
            Purse p = purseService.getOne(new QueryWrapper<Purse>().eq("user_id", loginUser.getId()));
            session.setAttribute("purse", p);

            orders.setOrderDate(DateUtils.nowTime());
            orders.setOrderState(1);
            orders.setUserId(loginUser.getId());
            Goods goods = goodsService.getById(orders.getGoodsId());
            //设置卖家ID
            orders.setSellerId(goods.getUserId());

            ordersService.addOrder(orders,p);
            return ResultCommon.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }

    /**
     * 查询我的订单
     * @param session
     * @return
     */
    @GetMapping("/myOrders")
    public String myOrders( HttpSession session,Model model){
        User loginUser = (User) session.getAttribute("loginUser");
        //买入的订单数据
        List<Orders> buy_orders = ordersService.list(new QueryWrapper<Orders>().eq("user_id", loginUser.getId()));
        for (Orders buy_order : buy_orders) {
            Integer goodsId = buy_order.getGoodsId();
            buy_order.setGoods(goodsService.getById(goodsId));
        }
        //卖出去的订单数据
        List<Orders> seller_orders = ordersService.list(new QueryWrapper<Orders>().eq("seller_id", loginUser.getId()));
        for (Orders buy_order : seller_orders) {
            Integer goodsId = buy_order.getGoodsId();
            buy_order.setGoods(goodsService.getById(goodsId));
        }
        model.addAttribute("buy_orders",buy_orders);
        model.addAttribute("seller_orders",seller_orders);
        return "/user/orders";
    }

    /**
     * 订单发货
     * @param orderId
     * @return
     */
    @ResponseBody
    @RequestMapping("/ordersDeliver/{orderId}")
    public ResultCommon ordersDeliver(@PathVariable("orderId") Integer orderId){
        try {
            Orders orders = ordersService.getById(orderId);
            orders.setOrderState(2);
            ordersService.updateById(orders);
            return ResultCommon.success(ResultCode.SUCCESS);
        } catch (Exception e){
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }


    /**
     * 订单收货
     * @param orderId
     * @return
     */
    @ResponseBody
    @RequestMapping("/ordersReceipt/{orderId}")
    public ResultCommon ordersReceipt(@PathVariable("orderId") Integer orderId){
        try {
            Orders orders = ordersService.getById(orderId);
            orders.setOrderState(3);
            ordersService.updateById(orders);
            return ResultCommon.success(ResultCode.SUCCESS);
        } catch (Exception e){
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }


}
