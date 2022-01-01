package com.one.blog.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configurable                                                // 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@EnableWebSecurity                                           // 필터 등록 :  스프링 시큐리티가 활성화되어 파일을 관리
@EnableGlobalMethodSecurity(prePostEnabled = true)           // 특정 주소로 접근을 하면 권한 및 인증을 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Bean       // IOC 등록
//    public BCryptPasswordEncoder encoder(){
//        return new BCryptPasswordEncoder();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/auth/**")
                    .permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/auth/loginForm");
    }
}
