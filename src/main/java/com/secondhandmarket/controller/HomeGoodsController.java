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

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class HomeGoodsController {
    @Autowired
    IImageService imageService;

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
        //fixme 适合商品少的项目
        QueryWrapper<Goods> allQueryWrapper=new QueryWrapper<Goods>();
        allQueryWrapper.eq("isdel",0);
        allQueryWrapper.eq("status",1);
        List<Goods> allGoods = goodsService.list(allQueryWrapper);
        //在内存中排序、切分
        //获取最新发布的商品
        List<Goods> goodsList0=allGoods.stream().sorted(Comparator.comparing(Goods::getPolishTime).reversed()).limit(6).collect(Collectors.toList());
        //按照catelog_id分组
        Map<Integer, List<Goods>> map = allGoods.stream().collect(Collectors.groupingBy(Goods::getCatelogId));
        //分组以后的在按照发布时间排序
        List<Goods> goodsList1 = map.get(1).stream().sorted(Comparator.comparing(Goods::getPolishTime).reversed()).limit(6).collect(Collectors.toList());
        List<Goods> goodsList2 = map.get(2).stream().sorted(Comparator.comparing(Goods::getPolishTime).reversed()).limit(6).collect(Collectors.toList());
        List<Goods> goodsList3 = map.get(3).stream().sorted(Comparator.comparing(Goods::getPolishTime).reversed()).limit(6).collect(Collectors.toList());
        List<Goods> goodsList4 = map.get(4).stream().sorted(Comparator.comparing(Goods::getPolishTime).reversed()).limit(6).collect(Collectors.toList());
        List<Goods> goodsList5 = map.get(5).stream().sorted(Comparator.comparing(Goods::getPolishTime).reversed()).limit(6).collect(Collectors.toList());
        List<Goods> goodsList6 = map.get(6).stream().sorted(Comparator.comparing(Goods::getPolishTime).reversed()).limit(6).collect(Collectors.toList());
        List<Goods> goodsList7 = map.get(7).stream().sorted(Comparator.comparing(Goods::getPolishTime).reversed()).limit(6).collect(Collectors.toList());

        //在内存中做计算
        List<Integer> allGoodsId = goodsList0.stream().map(Goods::getId).collect(Collectors.toList());
        allGoodsId.addAll(goodsList1.stream().map(Goods::getId).collect(Collectors.toList()));
        allGoodsId.addAll(goodsList2.stream().map(Goods::getId).collect(Collectors.toList()));
        allGoodsId.addAll(goodsList3.stream().map(Goods::getId).collect(Collectors.toList()));
        allGoodsId.addAll(goodsList4.stream().map(Goods::getId).collect(Collectors.toList()));
        allGoodsId.addAll(goodsList5.stream().map(Goods::getId).collect(Collectors.toList()));
        allGoodsId.addAll(goodsList6.stream().map(Goods::getId).collect(Collectors.toList()));
        allGoodsId.addAll(goodsList7.stream().map(Goods::getId).collect(Collectors.toList()));
        List<Image> goods_id = iImageService.list(new QueryWrapper<Image>().in("goods_id", allGoodsId));

        HashMap<Integer, String> collect = goods_id.stream().collect(Collectors.toMap(Image::getGoodsId, Image::getImgUrl, (k1, k2) -> k2, HashMap::new));

        for (Goods goods : goodsList0) {
            goods.setImageUrl(collect.get(goods.getId()));
        }
        model.addAttribute("goodsList0",goodsList0);

        for (Goods goods : goodsList1) {
            goods.setImageUrl(collect.get(goods.getId()));
        }
        model.addAttribute("goodsList1",goodsList1);

        for (Goods goods : goodsList2) {
            goods.setImageUrl(collect.get(goods.getId()));
        }
        model.addAttribute("goodsList2",goodsList2);

        for (Goods goods : goodsList3) {
            goods.setImageUrl(collect.get(goods.getId()));
        }
        model.addAttribute("goodsList3",goodsList3);

        for (Goods goods : goodsList4) {
            goods.setImageUrl(collect.get(goods.getId()));
        }
        model.addAttribute("goodsList4",goodsList4);

        for (Goods goods : goodsList5) {
            goods.setImageUrl(collect.get(goods.getId()));
        }
        model.addAttribute("goodsList5",goodsList5);

        for (Goods goods : goodsList6) {
            goods.setImageUrl(collect.get(goods.getId()));
        }
        model.addAttribute("goodsList6",goodsList6);

        for (Goods goods : goodsList7) {
            goods.setImageUrl(collect.get(goods.getId()));
        }
        model.addAttribute("goodsList7",goodsList7);
        //todo 查询公告栏信息
        //每日推荐
        model.addAttribute("today_recommend","1、官方的丧失对嘎达是十多个的撒个啥\\n2、是干啥的感觉扣水电费开机的时候高科技十多个\n3、是的嘎洒见到过客户反馈到撒后方可搭嘎开始哭丧和速度快");
        //违规信息
        model.addAttribute("violation_info","Codersyn, 一个热爱分享的博客阿范德萨孤独颂歌发打发的撒嘎嘎萨芬的撒大多数嘎达事发地是开工卡的江苏高考劳烦的开关和喀什欢哥");
        //老黄历
        model.addAttribute("almanac","Codersyn, 一个热爱分享的博客阿范德萨孤独颂歌发打发的撒嘎嘎萨芬的撒大多数嘎达事发地是开工卡的江苏高考劳烦的开关和喀什欢哥");
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
        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();
        if(catelog_id!=8){
            queryWrapper.eq("catelog_id",catelog_id);
        }
        queryWrapper.eq("isdel",0);
        queryWrapper.eq("status",1);
        queryWrapper.orderByDesc("polish_time");
        List<Goods> goodsList = goodsService.list(queryWrapper);  //最近6个手机数码商品
        List<Integer> goodsIds = goodsList.stream().map(Goods::getId).collect(Collectors.toList());
        List<Image> images = iImageService.list(new QueryWrapper<Image>().in("goods_id", goodsIds));
        Map<Integer, String> collect = images.stream().collect(Collectors.toMap(Image::getGoodsId, Image::getImgUrl, (k1, k2) -> k2));

        for (Goods goods : goodsList) {
            goods.setImageUrl(collect.get(goods.getId()));
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
            List<Integer> goodsIds = goodsList.stream().map(Goods::getId).collect(Collectors.toList());
            List<Image> images = iImageService.list(new QueryWrapper<Image>().in("goods_id", goodsIds));
            Map<Integer, String> collect = images.stream().collect(Collectors.toMap(Image::getGoodsId, Image::getImgUrl, (k1, k2) -> k2));
            for (Goods goods : goodsList) {
                goods.setImageUrl(collect.get(goods.getId()));
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

    /**
     * 跳转举报页面
     * @return
     */
    @GetMapping("/toInform/{goodsId}")
    public String toPay(@PathVariable("goodsId") Integer goodsId, Model model){
        Goods goods = goodsService.getById(goodsId);
        List<Image> images = imageService.list(new QueryWrapper<Image>().eq("goods_id", goodsId));
        if(images.size()>0){
            String imageUrl = images.get(0).getImgUrl();
            goods.setImageUrl(imageUrl);
        }
        model.addAttribute("goods",goods);
        return "/user/informGoods";
    }

}
