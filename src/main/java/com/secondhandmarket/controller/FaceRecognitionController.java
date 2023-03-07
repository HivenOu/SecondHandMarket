package com.secondhandmarket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/faceRecognition")
@RequiredArgsConstructor
public class FaceRecognitionController {
    private final ObjectMapper objectMapper;
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
    /*@RequestMapping(value = "/save.do")
    @ResponseBody
    public Map<String, Object> queryService(@RequestParam("the_file") MultipartFile file) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        try {
            //将数据转为流
            InputStream content = file.getInputStream();
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = content.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            //获得二进制数组
            byte[] in2b = swapStream.toByteArray();
            //调用人脸检测的方法
            Map<String, String>  strmap = FaceDetect.detectby(in2b);
            //将map数据封装json传到前台
            //此处JsonUtil未提供，大家可以找一个转json的方法
            String strjson = objectMapper.writeValueAsString(strmap);
            modelMap.put("strjson", strjson);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("data", e.getMessage());
        }
        return modelMap;
    }*/
}
