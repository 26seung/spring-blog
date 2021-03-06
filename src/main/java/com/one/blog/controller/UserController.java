package com.one.blog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.one.blog.domain.KakaoProfile;
import com.one.blog.domain.OAuthToken;
import com.one.blog.domain.User;
import com.one.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Controller
public class UserController {

    public static final String redirect_uri = "http://localhost:8080/auth/kakao/callback";
    public static final String client_id = "759b749f2e8e045f970194c82dc89fce";
    public static final String client_secret = "ZUfmWeB1SsPqBJPFc1jI5HwLifflMqY8";

    @Autowired
    UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${seung.key}")
    private String seungKey;

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
    public String kakaoCallback(String code){

        // RestTemplate ??? ???????????? http ????????? ???????????? ???????????? ?????? ?????????????????????
        RestTemplate rt = new RestTemplate();

        // HttpHeader ???????????? ??????
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody ???????????? ??????
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", client_id);
        params.add("redirect_uri", redirect_uri);
        params.add("code", code);
        params.add("client_secret", client_secret);

        // HttpHeader ???  HttpBody ??? ????????? ??????????????? ??????
        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest =
                new HttpEntity<>(params,headers);
        // Http ????????????
        ResponseEntity<String> res = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oAuthToken = null;
        try {
            oAuthToken = objectMapper.readValue(res.getBody(),OAuthToken.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("access token : "+oAuthToken.getAccess_token());

        // ????????? ?????? ????????????
        RestTemplate rt2 = new RestTemplate();

        // HttpHeader ???????????? ??????
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization","Bearer " + oAuthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpHeader ???  HttpBody ??? ????????? ??????????????? ??????
        HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest2 =
                new HttpEntity<>(headers2);
        // Http ????????????
        ResponseEntity<String> res2 = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest2,
                String.class
        );

        System.out.println(res2.getBody());

        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper2.readValue(res2.getBody(),KakaoProfile.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("????????? ????????? : " + kakaoProfile.getId());
        System.out.println("????????? ????????? : " + kakaoProfile.getKakao_account().getEmail());

        User kakaoUser = User.builder()
                .username(kakaoProfile.kakao_account.getEmail() + "_" + kakaoProfile.getId())
                .password(seungKey)
                .email(kakaoProfile.kakao_account.getEmail())
                .oauth("kakao")
                .build();

        User originUser = userService.????????????(kakaoUser.getUsername());

        if(originUser.getUsername() == null){
            System.out.println("-----?????? ?????? ?????????----");
            userService.????????????(kakaoUser);
        }
        System.out.println("-----?????? ????????? ???????????????.------");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), seungKey));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";
    }
}
