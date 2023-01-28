package com.secondhandmarket.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.secondhandmarket.pojo.Notice;
import com.secondhandmarket.pojo.User;
import com.secondhandmarket.service.INoticeService;
import com.secondhandmarket.service.IUserService;
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
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    INoticeService noticeService;

    @Autowired
    IUserService userService;

    /**
     * 跳转个人中心
     * @param model
     * @return
     */
    @GetMapping("/toHome")
    public String toHome(Model model){
        List<Notice> noticeList = noticeService.list(new QueryWrapper<Notice>().orderByDesc("create_at"));
        for (Notice notice : noticeList) {
            User user = userService.getById(notice.getUserId());
            notice.setUser(user);
        }
        model.addAttribute("noticeList",noticeList);
        return "/user/home";
    }

    /**
     * 发布求购信息
     * @param context
     * @return
     */
    @ResponseBody
    @PostMapping("/insertSelective")
    public ResultCommon insertSelective(String context,HttpSession session){
        try {
            User loginUser= (User) session.getAttribute("loginUser");
            Notice notice=new Notice();
            notice.setContext(context);
            notice.setUserId(loginUser.getId());
            notice.setStatus(0);
            notice.setCreateAt(DateUtils.nowTime());
            noticeService.save(notice);
            return ResultCommon.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }

}
