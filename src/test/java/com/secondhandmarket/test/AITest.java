package com.secondhandmarket.test;

import cn.hutool.core.io.IoUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.secondhandmarket.pojo.baidu.UserResp;
import com.secondhandmarket.utils.AuthService;
import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class AITest {

    @Test
    public void test(){
        String auth = AuthService.getAuth();
        System.out.println(auth);
        RestTemplate build = new RestTemplateBuilder().build();
        File file = new File("/Users/mac/Downloads/691678181489_.pic.jpg");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] bytes = IoUtil.read(fileInputStream).toByteArray();
        String s1 = Base64.getEncoder().encodeToString(bytes);
        Map<String, Object> map = new HashMap<>();
        map.put("image", s1);
        map.put("image_type", "BASE64");
        map.put("group_id_list", "hiven");
        map.put("quality_control", "LOW");
        ObjectMapper objectMapper = new ObjectMapper();
        String s;
        try {
             s = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=UTF-8");
        // 请求
        HttpEntity<String> request = new HttpEntity<>(s, headers);
        ResponseEntity<UserResp> userRespResponseEntity = build.postForEntity("https://aip.baidubce.com/rest/2.0/face/v3/search?access_token=" + auth, request, UserResp.class);
        UserResp body = userRespResponseEntity.getBody();
        System.out.println(body.getResult().getFaceToken());

    }
}
