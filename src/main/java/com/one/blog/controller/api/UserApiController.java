package com.one.blog.controller.api;

import com.one.blog.domain.User;
import com.one.blog.dto.ResponseDto;
import com.one.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController("/api")
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/join")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("UserApiController : save 호출");
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK,200);
    }
    @PostMapping("/user/login")
    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
        System.out.println("UserApiController : login 호출");
        User principal = userService.로그인(user);

        if(principal != null){
            session.setAttribute("principal",principal);
        }
        return new ResponseDto<Integer>(HttpStatus.OK,200);
    }
}
