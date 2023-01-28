package com.secondhandmarket.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.secondhandmarket.pojo.*;
import com.secondhandmarket.service.IFocusService;
import com.secondhandmarket.service.IGoodsService;
import com.secondhandmarket.service.IImageService;
import com.secondhandmarket.utils.DateUtils;
import com.secondhandmarket.utils.ResultCode;
import com.secondhandmarket.utils.ResultCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
@RequestMapping("/focus")
public class FocusController {

    @Autowired
    IFocusService focusService;

    @Autowired
    IGoodsService goodsService;

    @Autowired
    IImageService imageService;

    /**
     * 商品关注
     * @return
     */
    @ResponseBody
    @PostMapping("/addFocus")
    public ResultCommon addFocus(Integer goodsId, HttpSession session){
        try {
            User loginUser= (User) session.getAttribute("loginUser");
            Focus focusServiceOne = focusService.getOne(new QueryWrapper<Focus>().eq("goods_id", goodsId).eq("user_id", loginUser.getId()));
            if(focusServiceOne==null){
                Focus focus=new Focus();
                focus.setGoodsId(goodsId);
                focus.setUserId(loginUser.getId());
                focusService.save(focus);
                return ResultCommon.success(ResultCode.SUCCESS);
            }else{
                return ResultCommon.fail(ResultCode.REPPET);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }


    /**
     * 查询我的关注
     * @return
     */
    @GetMapping("/myFocus")
    public String myFocus(Model model, HttpSession session){
        User loginUser= (User) session.getAttribute("loginUser");
        List<Focus> focusList = focusService.list(new QueryWrapper<Focus>().eq("user_id", loginUser.getId()));
        List<Goods> goodsList=new ArrayList<Goods>();
        for (Focus focus : focusList) {
            Goods goods = goodsService.getById(focus.getGoodsId());
            List<Image> images = imageService.list(new QueryWrapper<Image>().eq("goods_id", goods.getId()));
            if(images.size()>0){
                String imageUrl = images.get(0).getImgUrl();
                goods.setImageUrl(imageUrl);
            }
            goodsList.add(goods);
        }
        model.addAttribute("goodsList",goodsList);
        return "/user/focus";
    }



    /**
     * 取消关注
     * @return
     */
    @ResponseBody
    @PostMapping("/cancelFocus")
    public ResultCommon cancelFocus(Integer goodsId, HttpSession session){
        try {
            User loginUser= (User) session.getAttribute("loginUser");
            focusService.remove(new QueryWrapper<Focus>().eq("goods_id", goodsId).eq("user_id", loginUser.getId()));
            return ResultCommon.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }

}
