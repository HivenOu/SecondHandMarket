package com.secondhandmarket.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Strings;
import com.secondhandmarket.pojo.Goods;
import com.secondhandmarket.pojo.Image;
import com.secondhandmarket.pojo.Purse;
import com.secondhandmarket.pojo.User;
import com.secondhandmarket.service.IGoodsService;
import com.secondhandmarket.service.IImageService;
import com.secondhandmarket.service.IPurseService;
import com.secondhandmarket.service.IUserService;
import com.secondhandmarket.utils.DateUtils;
import com.secondhandmarket.utils.ResultCode;
import com.secondhandmarket.utils.ResultCommon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
public class HomeUserController {

    @Autowired
    IUserService userService;

    @Autowired
    IPurseService purseService;

    @Autowired
    IGoodsService goodsService;

    @Autowired
    IImageService iImageService;

    /**
     * 01-商城会员登录
     *
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public ResultCommon login(User user, HttpSession session, HttpServletRequest request) {
        User loginUser = userService.getOne(new QueryWrapper<User>().eq("phone", user.getPhone()).eq("password", user.getPassword()).eq("isdel", 0));
        if (loginUser != null) {
            if (Strings.isNullOrEmpty(user.getRole())){
                return ResultCommon.fail(ResultCode.ROLE_NULL);
            }
            if (loginUser.getStatus() == 0) {
                session.setAttribute("loginUser", loginUser);

                //钱包
                Purse purse = purseService.getOne(new QueryWrapper<Purse>().eq("user_id", loginUser.getId()));
                session.setAttribute("purse", purse);

                //登录成功获取IP地址，更新到数据库！数据库存储最近一次登录的IP地址
                String ip = request.getRemoteAddr();
                loginUser.setLastLogin(ip);
                userService.updateById(loginUser);
                return ResultCommon.success(ResultCode.SUCCESS);
            } else {
                //账户被冻结
                return ResultCommon.fail(ResultCode.DONGJIE_PHONE_PASSWORD);
            }
        } else {
            //账户密码错误
            return ResultCommon.fail(ResultCode.ERROR_PHONE_PASSWORD);
        }
    }

    /**
     * 前台会员注销
     *
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";  //重定向首页
    }


    /**
     * 会员注册
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public ResultCommon register(User user) {
        try {
            user.setIsdel(0);
            user.setStatus(0);
            user.setPower(100);
            user.setGoodsNum(0);
            user.setCreateAt(DateUtils.nowTime());
            userService.register(user);
            return ResultCommon.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }


    /**
     * 跳转商品发布页面
     *
     * @return
     */
    @GetMapping("/topubGoods")
    public String topubGoods() {
        return "/user/pubGoods";
    }


    /**
     * 商品发布
     *
     * @param goods
     * @param imgUrl
     * @param response
     * @throws Exception
     */
    @PostMapping("/publishGoodsSubmit")
    public void publishGoodsSubmit(HttpSession session, Goods goods, String imgUrl, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        System.out.println("goods=" + goods);
        System.out.println("imgUrl=" + imgUrl);
        User loginUser = (User) session.getAttribute("loginUser");
        goodsService.addGoods(goods, imgUrl, loginUser);
        response.getWriter().write("<script>alert('发布商品成功!');location.href='/topubGoods';</script>");
    }

    /**
     * 查询发布的商品
     *
     * @return
     */
    @GetMapping("/findMyPublishGoods")
    public String findMyPublishGoods(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        List<Goods> goodsList = goodsService.list(new QueryWrapper<Goods>().eq("isdel", 0).eq("user_id", loginUser.getId()));
        for (Goods goods : goodsList) {
            List<Image> images = iImageService.list(new QueryWrapper<Image>().eq("goods_id", goods.getId()));
            if (images.size() > 0) {
                String imageUrl = images.get(0).getImgUrl();
                goods.setImageUrl(imageUrl);
            }
        }
        model.addAttribute("goodsList", goodsList);
        return "/user/goods";
    }


    /**
     * 个人设置页面
     * @return
     */
    @GetMapping("/toBasic")
    public String toBasic() {
        return "/user/basic";
    }


    /**
     * 个人信息更新
     * @param user
     */
    @ResponseBody
    @PostMapping("/updateInfo")
    public ResultCommon updateInfo(User user,HttpSession session) {
        try {
            userService.updateById(user);
            User loginUser = userService.getById(user.getId());
            session.setAttribute("loginUser", loginUser);
            return ResultCommon.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }

    /**
     * 个人用户名更新
     * @param username
     */
    @ResponseBody
    @PostMapping("/changeName")
    public ResultCommon changeName(String username,HttpSession session) {
        try {
            User loginUser = (User) session.getAttribute("loginUser");
            loginUser.setUsername(username);
            userService.updateById(loginUser);
            session.setAttribute("loginUser", loginUser);
            return ResultCommon.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }

}
