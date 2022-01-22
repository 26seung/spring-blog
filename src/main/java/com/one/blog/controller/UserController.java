package com.one.blog.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserController {

    public static final String redirect_uri = "http://localhost:8080/auth/kakao/callback";
    public static final String client_id = "759b749f2e8e045f970194c82dc89fce";
    public static final String client_secret = "ZUfmWeB1SsPqBJPFc1jI5HwLifflMqY8";

    @GetMapping("/auth/joinForm")
    public String joinForm(){
        return "user/joinForm";
    }
    @GetMapping("/auth/loginForm")
    public String loginForm(){
        return "user/loginForm";
    }
    @GetMapping("/user/updateForm")
    public String updateForm(){
        return "user/updateForm";
    }
    @GetMapping("/auth/kakao/callback")
    public @ResponseBody String kakaoCallback(String code){

        // RestTemplate 를 사용하면 http 요청을 간편하게 사용할수 있는 라이브러리이다
        RestTemplate rt = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트 생성
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", client_id);
        params.add("redirect_uri", redirect_uri);
        params.add("code", code);
        params.add("client_secret", client_secret);

        // HttpHeader 와  HttpBody 를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest =
                new HttpEntity<>(params,headers);
        // Http 요청하기
        ResponseEntity<String> res = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );


        return res.getBody();
    }
}
