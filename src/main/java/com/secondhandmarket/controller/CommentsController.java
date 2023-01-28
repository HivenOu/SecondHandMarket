package com.secondhandmarket.controller;


import com.secondhandmarket.pojo.Comments;
import com.secondhandmarket.pojo.User;
import com.secondhandmarket.service.ICommentsService;
import com.secondhandmarket.utils.DateUtils;
import com.secondhandmarket.utils.ResultCode;
import com.secondhandmarket.utils.ResultCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bruce
 * @since 2021-11-02
 */
@Controller
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    ICommentsService commentsService;

    /**
     * 商品评论
     * @param comments
     * @param session
     * @return
     */
    @PostMapping("/addComments")
    @ResponseBody
    public ResultCommon addComments(Comments comments, HttpSession session){
        try {
            User user=(User) session.getAttribute("loginUser");
            comments.setCreateAt(DateUtils.nowTime());
            comments.setUserId(user.getId());
            commentsService.save(comments);
            return ResultCommon.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }

}
