package com.secondhandmarket.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.secondhandmarket.pojo.Purse;
import com.secondhandmarket.pojo.User;
import com.secondhandmarket.service.IPurseService;
import com.secondhandmarket.service.IUserService;
import com.secondhandmarket.utils.PageUtil;
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
 * @since 2021-11-03
 */
@Controller
@RequestMapping("/purse")
public class PurseController {


    @Autowired
    IPurseService purseService;

    @Autowired
    IUserService userService;

    /**
     * 跳转充值提现页面
     * @return
     */
    @GetMapping("/toPurse")
    public String toPurse(HttpSession session){

        //

        //钱包
        User loginUser = (User) session.getAttribute("loginUser");
        Purse purse = purseService.getOne(new QueryWrapper<Purse>().eq("user_id", loginUser.getId()));
        session.setAttribute("purse", purse);

        return "/user/purse";
    }

    /**
     * 跳转钱包充值或者提现结果界面
     * @return
     */
    @GetMapping("/toPurseResult")
    public String toPurseResult(HttpSession session){

        //钱包
        User loginUser = (User) session.getAttribute("loginUser");
        Purse purse = purseService.getOne(new QueryWrapper<Purse>().eq("user_id", loginUser.getId()));
        session.setAttribute("purse", purse);

        return "/user/purse-result";
    }


    /**
     * 提交充值申请
     * @param id
     * @param recharge
     * @return
     */
    @ResponseBody
    @PostMapping("/recharge")
    public ResultCommon recharge(Integer id, Float recharge, HttpSession session){
        try {
            Purse purse=new Purse();
            purse.setId(id);
            purse.setRecharge(recharge);
            purse.setState(0); //审核中
            purseService.updateById(purse);

            //钱包--钱包同步
            User loginUser = (User) session.getAttribute("loginUser");
            Purse p = purseService.getOne(new QueryWrapper<Purse>().eq("user_id", loginUser.getId()));
            session.setAttribute("purse", p);

            return ResultCommon.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }


    /**
     * 申请提现
     * @param id
     * @param withdrawals
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("/withdrawals")
    public ResultCommon withdrawals(Integer id, Float withdrawals, HttpSession session){

        try {
            Purse purse=new Purse();
            purse.setId(id);
            purse.setWithdrawals(withdrawals);
            purse.setState(0); //审核中
            purseService.updateById(purse);

            //钱包--钱包同步
            User loginUser = (User) session.getAttribute("loginUser");
            Purse p = purseService.getOne(new QueryWrapper<Purse>().eq("user_id", loginUser.getId()));
            session.setAttribute("purse", p);

            return ResultCommon.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }


    /**
     * 01-钱包列表分页
     * @return
     */
    @RequestMapping("/page")
    public String page(@RequestParam(value = "userId",defaultValue = "") String userId,
                       @RequestParam(value = "state",defaultValue = "-1") Integer state,
                       @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                       @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize, Model model){
        QueryWrapper<Purse> queryWrapper=new QueryWrapper<Purse>();
        if(!"".equals(userId)){
            queryWrapper.eq("user_id",userId);
        }
        if(-1!=state){
            queryWrapper.eq("state",state);
        }
        //分页之后对象
        IPage page = purseService.page(new Page(pageIndex, pageSize), queryWrapper);

        List<Purse> records = page.getRecords();
        for (Purse record : records) {
            User user = userService.getById(record.getUserId());
            if(user!=null){
                record.setUserName(user.getUsername());
            }
        }

        // 封装一个分页工具类
        PageUtil pageUtil=new PageUtil(pageIndex,pageSize,page.getTotal(),records);

        model.addAttribute("pageUtil",pageUtil);
        model.addAttribute("userId",userId);//数据回显
        model.addAttribute("state",state);//数据回显
        return "/admin/purse/purse_list";
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @GetMapping("/getPurseById/{id}")
    @ResponseBody
    public ResultCommon getPurseById(@PathVariable("id") Integer id){
        try {
            Purse purse = purseService.getById(id);
            return ResultCommon.success(ResultCode.SUCCESS,purse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }


    /**
     * 审核通过---充值和提现
     * @return
     */
    @PostMapping("/updatePursePass")
    @ResponseBody
    public ResultCommon updatePursePass(Purse purse){
        try {
            if(purse!=null&&purse.getRecharge()!=null){
                purse.setBalance(purse.getBalance()+purse.getRecharge());
            }else if(purse!=null&&purse.getWithdrawals()!=null){
                purse.setBalance(purse.getBalance()-purse.getWithdrawals());
            }
            //purse.setRecharge(null);//没起作用？
            purse.setState(2); //审核成功
            purseService.update(purse);
            return ResultCommon.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }


    /**
     * 审核不通过 ---充值和提现
     * @return
     */
    @PostMapping("/updatePurseRefuse")
    @ResponseBody
    public ResultCommon updatePurseRefuse(Purse purse){
        try {
            //purse.setRecharge(null);//没起作用？
            purse.setState(1); //审核不通过
            purseService.update(purse);
            return ResultCommon.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }


    /**
     * 确认钱包
     * @return
     */
    @PostMapping("/myConfirm")
    @ResponseBody
    public ResultCommon myConfirm(Integer id){
        try {
            Purse purse = purseService.getById(id);
            purse.setRecharge(null);
            purse.setWithdrawals(null);
            purse.setState(null);
            int count = purseService.update(purse);
            return ResultCommon.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }
}
