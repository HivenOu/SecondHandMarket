package com.secondhandmarket.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.secondhandmarket.dto.UserDto;
import com.secondhandmarket.dto.UserResp;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取token类
 */
public class AuthService {

    // api: https://aip.baidubce.com/rest/2.0/face/v3/search
    //appid ： 31044248
    //apiKey； xONXRBZZezWyv35Dn4Ky4yxp
    // apiSecret ： Z9gC5eKD6MFjbInp7IB22nA3Q6E4pdLZ
    /**
     * 获取权限token
     * @return 返回示例：
     * {
     * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000
     * }
     */
    public static String getAuth() {
        // 官网获取的 API Key 更新为你注册的
        String clientId = "xONXRBZZezWyv35Dn4Ky4yxp";
        // 官网获取的 Secret Key 更新为你注册的
        String clientSecret = "Z9gC5eKD6MFjbInp7IB22nA3Q6E4pdLZ";
        return getAuth(clientId, clientSecret);
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(result);
            String access_token = jsonNode.get("access_token").asText();
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }

    public static List<UserDto> getFaceInfo(byte[] bytes){
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
        RestTemplate build = new RestTemplateBuilder().build();
        ResponseEntity<UserResp> userRespResponseEntity = build.postForEntity("https://aip.baidubce.com/rest/2.0/face/v3/search?access_token=" + getAuth(), request, UserResp.class);
        UserResp body = userRespResponseEntity.getBody();
        System.out.println(body.getResult().getFaceToken());
        return body.getResult().getUserDtoList();
    }

}