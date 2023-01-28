package com.secondhandmarket.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhandmarket.pojo.Admin;
import com.secondhandmarket.pojo.User;
import com.secondhandmarket.service.IUserService;
import com.secondhandmarket.utils.PageUtil;
import com.secondhandmarket.utils.ResultCode;
import com.secondhandmarket.utils.ResultCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bruce
 * @since 2021-10-29
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    /**
     * 01-用户分页
     * @return
     */
    @RequestMapping("/page")
    public String page(@RequestParam(value = "phone",defaultValue = "") String phone,
                       @RequestParam(value = "username",defaultValue = "") String username,
                       @RequestParam(value = "qq",defaultValue = "") String qq,
                       @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                       @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize, Model model){
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        queryWrapper.eq("isdel",0);//查询的隐藏条件，查询的是未删除的
        if(!"".equals(phone)){
            queryWrapper.like("phone",phone);
        }
        if(!"".equals(username)){
            queryWrapper.like("username",username);
        }
        if(!"".equals(qq)){
            queryWrapper.like("qq",qq);
        }
        //分页之后对象
        IPage page = userService.page(new Page(pageIndex, pageSize), queryWrapper);
        // 封装一个分页工具类
        PageUtil pageUtil=new PageUtil(pageIndex,pageSize,page.getTotal(),page.getRecords());

        model.addAttribute("pageUtil",pageUtil);
        model.addAttribute("phone",phone);//数据回显
        model.addAttribute("username",username);//数据回显
        model.addAttribute("qq",qq); //数据回显
        return "/admin/user/user_list";
    }


    /**
     * 02-根据ID查询
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    @ResponseBody
    public ResultCommon<User> findById(@PathVariable("id") Integer id){
        User user = userService.getById(id);
        return ResultCommon.success(ResultCode.SUCCESS,user);
    }


    /**
     * 03-更新User
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    @ResponseBody
    public ResultCommon updateUser(User user){
        boolean b = userService.updateById(user);
        if(b){
            return ResultCommon.success(ResultCode.SUCCESS);
        }else{
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }


    @PostMapping("/deleteUser")
    @ResponseBody
    public ResultCommon deleteUser(String ids){
        try {
            System.out.println("要删除的ID集合是："+ ids);
            //要删除的用户ID
            String[] userIds = ids.split(",");
            userService.deleteBatch(userIds);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCommon.fail(ResultCode.FAIL);
        }
        return ResultCommon.success(ResultCode.SUCCESS);
    }


}
