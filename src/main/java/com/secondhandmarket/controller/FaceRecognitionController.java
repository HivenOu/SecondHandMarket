package com.secondhandmarket.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.secondhandmarket.dto.UserDto;
import com.secondhandmarket.pojo.Purse;
import com.secondhandmarket.pojo.User;
import com.secondhandmarket.service.IPurseService;
import com.secondhandmarket.service.IUserService;
import com.secondhandmarket.utils.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/faceRecognition")
@RequiredArgsConstructor
public class FaceRecognitionController {
    private final ObjectMapper objectMapper;
    private final IUserService userService;
    private final IPurseService purseService;
    /**
     * 人脸检测测试页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/face.do")
    public ModelAndView queryVoi() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/face");
        return modelAndView;
    }

    /**
     * 请求人脸检测
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/save.do")
    @ResponseBody
    public Map<String, Object> queryService(@RequestParam("the_file") MultipartFile file, HttpSession session, HttpServletRequest request) throws JsonProcessingException {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        try {
            //将数据转为流
            byte[] bytes = IoUtil.readBytes(file.getInputStream());
            //调用人脸检测的方法
            List<UserDto> faceInfo = AuthService.getFaceInfo(bytes);
            Map<String, String> map = new HashMap<String,String>();
            //找到分数最高的那个用户信息
            if (CollUtil.isEmpty(faceInfo)){
                map.put("msg", "您还未录入人脸识别系统");
                map.put("code", "500");
                modelMap.put("success", true);
                String strjson = objectMapper.writeValueAsString(map);
                modelMap.put("strjson", strjson);
                return modelMap;
            }
            UserDto userDto = faceInfo.stream().sorted(Comparator.comparing(UserDto::getScore).reversed()).findFirst().get();
            User one = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getId, userDto.getUserId()));
            if(BeanUtil.isEmpty(one)){
                map.put("msg", "您账号异常，请联系管理员处理");
                map.put("code", "500");
                modelMap.put("success", true);
                String strjson = objectMapper.writeValueAsString(map);
                modelMap.put("strjson", strjson);
                return modelMap;
            }
            session.setAttribute("loginUser", one);
            //钱包
            Purse purse = purseService.getOne(new QueryWrapper<Purse>().eq("user_id", one.getId()));
            session.setAttribute("purse", purse);
            //登录成功获取IP地址，更新到数据库！数据库存储最近一次登录的IP地址
            String ip = request.getRemoteAddr();
            one.setLastLogin(ip);
            userService.updateById(one);
            map.put("name", one.getUsername());
            map.put("qq", one.getQq());
            map.put("msg", "成功");
            map.put("code", "200");
            map.put("phone", one.getPhone());
            //将map数据封装json传到前台
            //此处JsonUtil未提供，大家可以找一个转json的方法
            String strjson = objectMapper.writeValueAsString(map);
            modelMap.put("strjson", strjson);
            modelMap.put("success", true);
        } catch (Exception e) {
            Map<String, String> map = new HashMap<String,String>();
            map.put("msg", "服务器异常");
            map.put("code", "500");
            modelMap.put("success", true);
            String strjson = objectMapper.writeValueAsString(map);
            modelMap.put("strjson", strjson);
            e.printStackTrace();
        }
        return modelMap;
    }
}
