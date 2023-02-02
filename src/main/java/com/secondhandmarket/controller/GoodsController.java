package com.secondhandmarket.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Strings;
import com.secondhandmarket.pojo.Goods;
import com.secondhandmarket.pojo.GoodsInforms;
import com.secondhandmarket.pojo.User;
import com.secondhandmarket.service.IGoodsService;
import com.secondhandmarket.utils.PageUtil;
import com.secondhandmarket.utils.ResultCode;
import com.secondhandmarket.utils.ResultCommon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bruce
 * @since 2021-11-01
 */
@Controller
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    @Autowired
    IGoodsService goodsService;


    /**
     * 01-用户分页
     * @return
     */
    @RequestMapping("/page")
    public String page(@RequestParam(value = "id",defaultValue = "") String id,
                       @RequestParam(value = "name",defaultValue = "") String name,
                       @RequestParam(value = "status",defaultValue = "-1") String status,
                       @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                       @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize, Model model){
        QueryWrapper<Goods> queryWrapper=new QueryWrapper<Goods>();
        queryWrapper.eq("isdel",0);//查询的隐藏条件，查询的是未删除的
        if(!"".equals(id)){
            queryWrapper.eq("id",id);
        }
        if(!"".equals(name)){
            queryWrapper.like("name",name);
        }
        if(!"-1".equals(status)){
            queryWrapper.like("status",status);
        }
        //分页之后对象
        IPage page = goodsService.page(new Page(pageIndex, pageSize), queryWrapper);
        // 封装一个分页工具类
        PageUtil pageUtil=new PageUtil(pageIndex,pageSize,page.getTotal(),page.getRecords());

        model.addAttribute("pageUtil",pageUtil);
        model.addAttribute("id",id);//数据回显
        model.addAttribute("name",name);//数据回显
        model.addAttribute("status",status); //数据回显
        //todo 设置这个商品被举报的次数
        return "/admin/goods/goods_list";
    }


    /**
     * 02-根据ID查询
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    @ResponseBody
    public ResultCommon<Goods> findById(@PathVariable("id") Integer id){
        Goods goods = goodsService.getById(id);
        return ResultCommon.success(ResultCode.SUCCESS,goods);
    }


    /**
     * 03-更新
     * @param goods
     * @return
     */
    @PostMapping("/updateGoods")
    @ResponseBody
    public ResultCommon updateGoods(Goods goods){
        boolean b = goodsService.updateById(goods);
        if(b){
            return ResultCommon.success(ResultCode.SUCCESS);
        }else{
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }

    /**
     * 商品上下架
     * @param
     * @return
     */
    @PatchMapping("/updateStatus")
    @ResponseBody
    public ResultCommon updateStatus(@RequestParam String goodsId,@RequestParam Integer status){
        log.info(goodsId);
        log.info(status.toString());
        log.info("请求过来了");
        return ResultCommon.success(ResultCode.SUCCESS);
    }

    @PostMapping("/deleteGoods")
    @ResponseBody
    public ResultCommon deleteGoods(String ids){
        try {
            System.out.println("要删除的ID集合是："+ ids);
            //要删除的用户ID
            String[] goodsIds = ids.split(",");
            goodsService.deleteBatch(goodsIds);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCommon.fail(ResultCode.FAIL);
        }
        return ResultCommon.success(ResultCode.SUCCESS);
    }

    @PostMapping("/inform")
    @ResponseBody
    public ResultCommon informGoods(GoodsInforms informs, HttpSession session){
        if (Strings.isNullOrEmpty(informs.getInformInformation())){
            return ResultCommon.success(ResultCode.SUCCESS);
        }
        User loginUser = (User) session.getAttribute("loginUser");
        informs.setUserId(loginUser.getId());
        informs.setInformTime(new Date());
        goodsService.informGoods(informs);
        return ResultCommon.success(ResultCode.SUCCESS,informs.getGoodsId());
    }

}
