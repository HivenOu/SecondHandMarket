package com.secondhandmarket.controller;

import com.secondhandmarket.utils.ResultCommon;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UploadFileContrroller {


    /**
     * 执行文件上传
     * @return
     */
    @ResponseBody
    @RequestMapping("/uploadFile")
    public ResultCommon uploadFile(@RequestParam("myfile") MultipartFile file){
        ResultCommon resultCommon=null;
        try {
            //原来文件的名字  4444.jpg
            String originalFilename = file.getOriginalFilename();
            System.out.println("上传的原始文件名是:"+originalFilename);
            //获取文件的扩展名 .jpg
            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
            //生成一个不重复的文件名
            String fileName = UUID.randomUUID().toString()+extName;
            System.out.println("服务器生成的新的文件名是:"+fileName);
            //获取服务器文件上传的真实路径
            String filePath=this.getClass().getResource("/").getPath()+"/static/upload/"+fileName;
            System.out.println("文件上传最终的服务器路径是:"+filePath);
            //调用文件上传
            file.transferTo(new File(filePath));
            System.out.println("文件上传成功");
            resultCommon=new ResultCommon(200,fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件上传异常");
            resultCommon=new ResultCommon(500,null);
        }
        return resultCommon;
    }


}
