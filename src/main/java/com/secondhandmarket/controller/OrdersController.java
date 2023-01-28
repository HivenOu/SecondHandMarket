package com.secondhandmarket.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhandmarket.pojo.Goods;
import com.secondhandmarket.pojo.Orders;
import com.secondhandmarket.service.IOrdersService;
import com.secondhandmarket.utils.PageUtil;
import com.secondhandmarket.utils.ResultCode;
import com.secondhandmarket.utils.ResultCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    IOrdersService ordersService;

    /**
     * 01-用户分页
     * @return
     */
    @RequestMapping("/page")
    public String page(@RequestParam(value = "orderNum",defaultValue = "") String orderNum,
                       @RequestParam(value = "orderInformation",defaultValue = "") String orderInformation,
                       @RequestParam(value = "orderState",defaultValue = "-1") String orderState,
                       @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                       @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize, Model model){
        QueryWrapper<Orders> queryWrapper=new QueryWrapper<Orders>();
        if(!"".equals(orderNum)){
            queryWrapper.eq("order_num",orderNum);
        }
        if(!"".equals(orderInformation)){
            queryWrapper.like("order_information",orderInformation);
        }
        if(!"-1".equals(orderState)){
            queryWrapper.like("order_state",orderState);
        }
        //分页之后对象
        IPage page = ordersService.page(new Page(pageIndex, pageSize), queryWrapper);
        // 封装一个分页工具类
        PageUtil pageUtil=new PageUtil(pageIndex,pageSize,page.getTotal(),page.getRecords());

        model.addAttribute("pageUtil",pageUtil);
        model.addAttribute("orderNum",orderNum);//数据回显
        model.addAttribute("orderInformation",orderInformation);//数据回显
        model.addAttribute("orderState",orderState); //数据回显
        return "/admin/orders/orders_list";
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getOrdersById/{id}")
    public ResultCommon getOrdersById(@PathVariable("id") Integer id){
        Orders orders = ordersService.getById(id);
        return  ResultCommon.success(ResultCode.SUCCESS,orders);
    }

    /**
     * 订单数据更新
     * @param orders
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateOrders")
    public ResultCommon updateOrders(Orders orders){
        System.out.println("要更新的数据是:"+orders);
        try {
            ordersService.updateById(orders);
            return  ResultCommon.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return  ResultCommon.fail(ResultCode.FAIL);
        }
    }

}
