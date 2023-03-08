package com.secondhandmarket.controller;

import com.secondhandmarket.utils.ImageCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

@Controller
@Slf4j
public class CodeController {

    /**
     * 生成一张随机的验证码图片
     */
    @RequestMapping("/createCodeImage")
    public void createCodeImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BufferedImage image1 = ImageCodeUtils.getImage();
        request.getSession().setAttribute("checkcode", ImageCodeUtils.getText()); // 将随机数存在session中
        log.info("系统正确的验证码是:{}",ImageCodeUtils.getText());
        /*
         * 5.图形写给浏览器
         */
        response.setContentType("image/jpeg");
        // 发响应头控制浏览器不要缓存图片
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        ImageIO.write(image1, "jpg", response.getOutputStream());

    }

}
