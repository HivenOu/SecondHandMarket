package com.secondhandmarket.test;

import com.secondhandmarket.utils.ImageCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
public class CodeTest {



    /**
     * 获取验证码文本的方法
     *
     * @return
     */
    @Test
    public void getText() {
        log.info("获取到的文本字符为：{}",ImageCodeUtils.getText());
    }


    public static void output(BufferedImage image) throws IOException                  //将验证码图片写出的方法
    {
        OutputStream out = new FileOutputStream("E:\\hotelManagementSystem\\wanglele\\src\\assets\\yanzhengma\\a.jpg");  /// 将图片生成到 e盘下面
        ImageIO.write(image, "jpg", out);
    }

}
