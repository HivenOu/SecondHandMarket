package com.secondhandmarket.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.secondhandmarket.pojo.*;
import com.secondhandmarket.service.*;
import com.secondhandmarket.utils.DateUtils;
import com.secondhandmarket.utils.ResultCode;
import com.secondhandmarket.utils.ResultCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeGoodsController {


    @Autowired
    IGoodsService goodsService;

    @Autowired
    IImageService iImageService;


    @Autowired
    ICatelogService catelogService;


    @Autowired
    IUserService userService;


    @Autowired
    ICommentsService commentsService;

    /**
     * 01-前台首页
     * @return
     */
    @RequestMapping("/")
    public String findNewGoods(Model model){
        QueryWrapper<Goods> queryWrapper=new QueryWrapper<Goods>();
        queryWrapper.eq("isdel",0);
        queryWrapper.eq("status",1);
        queryWrapper.orderByDesc("polish_time");
        queryWrapper.last("limit 0,6");
        List<Goods> goodsList0 = goodsService.list(queryWrapper);  //最近6个商品

        QueryWrapper<Goods> queryWrapper1=new QueryWrapper<Goods>();
        queryWrapper1.eq("isdel",0);
        queryWrapper1.eq("status",1);
        queryWrapper1.orderByDesc("polish_time");
        queryWrapper1.eq("catelog_id",1);
        queryWrapper1.last("limit 0,6");
        List<Goods> goodsList1 = goodsService.list(queryWrapper1);  //最近6个手机数码商品


        QueryWrapper<Goods> queryWrapper2=new QueryWrapper<Goods>();
        queryWrapper2.eq("isdel",0);
        queryWrapper2.eq("status",1);
        queryWrapper2.orderByDesc("polish_time");
        queryWrapper2.eq("catelog_id",2);
        queryWrapper2.last("limit 0,6");
        List<Goods> goodsList2 = goodsService.list(queryWrapper2);  //最近6个运动户外商品

        QueryWrapper<Goods> queryWrapper3=new QueryWrapper<Goods>();
        queryWrapper3.eq("isdel",0);
        queryWrapper3.eq("status",1);
        queryWrapper3.orderByDesc("polish_time");
        queryWrapper3.eq("catelog_id",3);
        queryWrapper3.last("limit 0,6");
        List<Goods> goodsList3 = goodsService.list(queryWrapper3);  //最近6个日用电器商品

        QueryWrapper<Goods> queryWrapper4=new QueryWrapper<Goods>();
        queryWrapper4.eq("isdel",0);
        queryWrapper4.eq("status",1);
        queryWrapper4.orderByDesc("polish_time");
        queryWrapper4.eq("catelog_id",4);
        queryWrapper4.last("limit 0,6");
        List<Goods> goodsList4 = goodsService.list(queryWrapper4);  //最近6个图书教材商品

        QueryWrapper<Goods> queryWrapper5=new QueryWrapper<Goods>();
        queryWrapper5.eq("isdel",0);
        queryWrapper5.eq("status",1);
        queryWrapper5.orderByDesc("polish_time");
        queryWrapper5.eq("catelog_id",5);
        queryWrapper5.last("limit 0,6");
        List<Goods> goodsList5 = goodsService.list(queryWrapper5);  //最近6个服饰美妆商品

        QueryWrapper<Goods> queryWrapper6=new QueryWrapper<Goods>();
        queryWrapper6.eq("isdel",0);
        queryWrapper6.eq("status",1);
        queryWrapper6.orderByDesc("polish_time");
        queryWrapper6.eq("catelog_id",6);
        queryWrapper6.last("limit 0,6");
        List<Goods> goodsList6 = goodsService.list(queryWrapper6);  //最近6个体育器材商品

        QueryWrapper<Goods> queryWrapper7=new QueryWrapper<Goods>();
        queryWrapper7.eq("isdel",0);
        queryWrapper7.eq("status",1);
        queryWrapper7.orderByDesc("polish_time");
        queryWrapper7.eq("catelog_id",7);
        queryWrapper7.last("limit 0,6");
        List<Goods> goodsList7 = goodsService.list(queryWrapper7);  //最近6个生活百货商品

        for (Goods goods : goodsList0) {
            List<Image> images = iImageService.list(new QueryWrapper<Image>().eq("goods_id", goods.getId()));
            if(images.size()>0){
                String imageUrl = images.get(0).getImgUrl();
                goods.setImageUrl(imageUrl);
            }
        }
        model.addAttribute("goodsList0",goodsList0);


        for (Goods goods : goodsList1) {
            List<Image> images = iImageService.list(new QueryWrapper<Image>().eq("goods_id", goods.getId()));
            if(images.size()>0){
                String imageUrl = images.get(0).getImgUrl();
                goods.setImageUrl(imageUrl);
            }
        }
        model.addAttribute("goodsList1",goodsList1);


        for (Goods goods : goodsList2) {
            List<Image> images = iImageService.list(new QueryWrapper<Image>().eq("goods_id", goods.getId()));
            if(images.size()>0){
                String imageUrl = images.get(0).getImgUrl();
                goods.setImageUrl(imageUrl);
            }
        }
        model.addAttribute("goodsList2",goodsList2);

        for (Goods goods : goodsList3) {
            List<Image> images = iImageService.list(new QueryWrapper<Image>().eq("goods_id", goods.getId()));
            if(images.size()>0){
                String imageUrl = images.get(0).getImgUrl();
                goods.setImageUrl(imageUrl);
            }
        }
        model.addAttribute("goodsList3",goodsList3);

        for (Goods goods : goodsList4) {
            List<Image> images = iImageService.list(new QueryWrapper<Image>().eq("goods_id", goods.getId()));
            if(images.size()>0){
                String imageUrl = images.get(0).getImgUrl();
                goods.setImageUrl(imageUrl);
            }
        }
        model.addAttribute("goodsList4",goodsList4);

        for (Goods goods : goodsList5) {
            List<Image> images = iImageService.list(new QueryWrapper<Image>().eq("goods_id", goods.getId()));
            if(images.size()>0){
                String imageUrl = images.get(0).getImgUrl();
                goods.setImageUrl(imageUrl);
            }
        }
        model.addAttribute("goodsList5",goodsList5);

        for (Goods goods : goodsList6) {
            List<Image> images = iImageService.list(new QueryWrapper<Image>().eq("goods_id", goods.getId()));
            if(images.size()>0){
                String imageUrl = images.get(0).getImgUrl();
                goods.setImageUrl(imageUrl);
            }
        }
        model.addAttribute("goodsList6",goodsList6);

        for (Goods goods : goodsList7) {
            List<Image> images = iImageService.list(new QueryWrapper<Image>().eq("goods_id", goods.getId()));
            if(images.size()>0){
                String imageUrl = images.get(0).getImgUrl();
                goods.setImageUrl(imageUrl);
            }
        }
        model.addAttribute("goodsList7",goodsList7);

        return "/goods/homeGoods"; //后台首页
    }


    /**
     * 02-根据类名ID查询对应商品
     * @param catelog_id
     * @param model
     * @return
     */
    @GetMapping("/findNewGoodsByCatelogId/{catelog_id}")
    public String findNewGoodsByCatelogId(@PathVariable("catelog_id") Long catelog_id, Model model){
        QueryWrapper<Goods> queryWrapper=new QueryWrapper<Goods>();
        if(catelog_id!=8){
            queryWrapper.eq("catelog_id",catelog_id);
        }
        queryWrapper.eq("isdel",0);
        queryWrapper.eq("status",1);
        queryWrapper.orderByDesc("polish_time");
        List<Goods> goodsList = goodsService.list(queryWrapper);  //最近6个手机数码商品

        for (Goods goods : goodsList) {
            List<Image> images = iImageService.list(new QueryWrapper<Image>().eq("goods_id", goods.getId()));
            if(images.size()>0){
                String imageUrl = images.get(0).getImgUrl();
                goods.setImageUrl(imageUrl);
            }
        }

        model.addAttribute("goodsList",goodsList);
        model.addAttribute("catelog_id",catelog_id);
        return "/goods/catelogGoods"; //后台分类
    }


    /**
     * 01-首页搜索
     * @param keywords
     * @return
     */
    @PostMapping("/searchGoods")
    public String searchGoods(@RequestParam(value = "keywords",defaultValue = "") String keywords,Model model){
        List<Goods> goodsList=new ArrayList<Goods>();
        if(!keywords.equals("")){
            QueryWrapper<Goods> queryWrapper=new QueryWrapper<Goods>();
            queryWrapper.eq("isdel",0);
            queryWrapper.eq("status",1);
            queryWrapper.like("name",keywords);
            queryWrapper.orderByDesc("polish_time");
            goodsList = goodsService.list(queryWrapper);
            for (Goods goods : goodsList) {
                List<Image> images = iImageService.list(new QueryWrapper<Image>().eq("goods_id", goods.getId()));
                if(images.size()>0){
                    String imageUrl = images.get(0).getImgUrl();
                    goods.setImageUrl(imageUrl);
                }
            }
        }
        model.addAttribute("goodsList",goodsList);
        model.addAttribute("keywords",keywords);
        return "/goods/searchGoods";
    }

    /**
     * 商品详情
     * @param id
     * @return
     */
    @GetMapping("/goodsDetails/{id}")
    public String goodsDetails(@PathVariable("id") Integer id,Model model){
        Goods goods = goodsService.getById(id);
        List<Image> images = iImageService.list(new QueryWrapper<Image>().eq("goods_id", id));
        if(images.size()>0){
            String imageUrl = images.get(0).getImgUrl();
            goods.setImageUrl(imageUrl);
        }

        //所属的分类
        Catelog catelog = catelogService.getById(goods.getCatelogId());
        //卖家对象
        User seller = userService.getById(goods.getUserId());
        //查询商品评论数据
        List<Comments> commentsList = commentsService.list(new QueryWrapper<Comments>().eq("goods_id", id));
        for (Comments comments : commentsList) {
            Integer userId = comments.getUserId();
            User user = userService.getById(userId);
            comments.setUserName(user.getUsername());
        }

        model.addAttribute("goods",goods);

        model.addAttribute("catelog",catelog);
        model.addAttribute("seller",seller);
        model.addAttribute("commentsList",commentsList);

        return "/goods/detailGoods";
    }


    /**
     * 修改上下架状态
     * @param id
     * @param status
     * @return
     */
    @GetMapping("/updateStatus/{id}/{status}")
    @ResponseBody
    public ResultCommon updateStatus(@PathVariable("id") String id,@PathVariable("status") Integer status){
        try {
            Goods goods = goodsService.getById(id);
            goods.setStatus(status);
            if(status==0){
                //如果下架 有下架的日期.
                goods.setEndTime(DateUtils.nowTime());
            }
            goodsService.updateById(goods);

            return ResultCommon.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }

    /**
     *  个人中心-商品编辑
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/toeditGoods/{id}")
    public String toeditGoods(@PathVariable("id") Integer id,Model model){
        Goods goods = goodsService.getById(id);
        List<Image> images = iImageService.list(new QueryWrapper<Image>().eq("goods_id", id));
        if(images.size()>0){
            String imageUrl = images.get(0).getImgUrl();
            goods.setImageUrl(imageUrl);
        }

        model.addAttribute("goods",goods);

        return "/user/editGoods";
    }

    /**
     * 商品更新
     * @return
     */
    @PostMapping("/updateGoods")
    @ResponseBody
    public ResultCommon updateGoods(Goods goods,String imgUrl){
        try {
            System.out.println("要更新的对象是："+goods);
            goodsService.updateGoods(goods,imgUrl);
            return ResultCommon.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }

}