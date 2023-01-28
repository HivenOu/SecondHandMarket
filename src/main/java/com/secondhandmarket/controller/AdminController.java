package com.secondhandmarket.controller;

import com.secondhandmarket.pojo.Admin;
import com.secondhandmarket.service.AdminService;
import com.secondhandmarket.utils.ResultCode;
import com.secondhandmarket.utils.ResultCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    /**
     * 00-跳转到登录页面
     * @return
     */
    @GetMapping
    public String toLogin(){
        return "/admin/login";
    }

    /**
     * 01-管理员登录
     * @param admin
     * @return
     */
    @PostMapping("adminlogin")
    public String adminLogin(Admin admin, String code, Model model, HttpSession session){
        // 系统验证码
        String checkcode= (String) session.getAttribute("checkcode");
        if(checkcode.equalsIgnoreCase(code)){
            Admin loginAdmin = adminService.adminLogin(admin);
            if(loginAdmin!=null){
                //登录成功
                session.setAttribute("loginAdmin",loginAdmin);
                return "/admin/index";
            }else{
                model.addAttribute("msg","账号或密码错误!");
                //登录失败跳转管理员登录
                return "/admin/login";
            }
        }else{
            model.addAttribute("msg","验证码错误!");
            //登录失败跳转管理员登录
            return "/admin/login";
        }
    }

    /**
     * 管理员退出
     * @return
     */
    @GetMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "/admin/login";
    }

    @RequestMapping("/toInfo")
    public String toInfo(){
        return "/admin/info";
    }


    /**
     * 跳转更详细密码页面
     * @return
     */
    @RequestMapping("/tomodify")
    public String tomodify(){
        return "/admin/modify";
    }


    /**
     * 修改密码
     * @param password
     * @param password1
     * @return
     */
    @RequestMapping("/changePassword")
    @ResponseBody
    public ResultCommon changePassword(String password,String password1,HttpSession session){
        try {
            Admin loginAdmin=(Admin) session.getAttribute("loginAdmin");
            if(loginAdmin.getPassword().equals(password)){
                if(password1!=null&&!"".equals(password1)){
                    loginAdmin.setPassword(password1);
                    adminService.updateById(loginAdmin);
                    session.invalidate();//清空session
                    return ResultCommon.success(ResultCode.SUCCESS);
                }else {
                    return ResultCommon.success(ResultCode.BAD_NEWPASSWORD); // 505: 新密码不能为空
                }
            }else{
                return ResultCommon.fail(ResultCode.BAD_PASSWORD); // 504 原密码输入错误
            }
        } catch (Exception e) {
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }
}
