package com.soft.one.ewms.business.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Title：服务安全配置
 * Description：拦截器功能,认证过程，全局方法拦截，都得先经过这个配置
 * @author WZQ
 * @version 1.0.0
 * @date 2020/2/16
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)//数据通过类型
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    //密码编码器，密码匹配的加密算法
    //BCrypt随机不可解加密算法
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        //安全密码，有md4.md5,SHA-1,SHA-256
    }

    // 忽略该地址，无需携带token，直接就可以被访问授权
    // 静态资源被拦截
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/is/login")
                .antMatchers("/swagger-ui.html");
    }

    /**
     * 设置访问权限
     * web授权或者方法授权
     * web写死在这，方法是写在每一个controller上，采用方法授权比较好
     * 基于角色可扩展性不强，推荐基于资源（基于权限）
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 请求头都必须带token
        http.csrf().disable() // 可post
                .authorizeRequests()
                .antMatchers("/user/**", "/function/**", "/operation/**").authenticated() // 这个url下都必须经过认证
                .anyRequest().permitAll() // 其他允许通过
                ;
    }
}