package com.yang.shirospringboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/09/20:45
 */
@RequestMapping
@Controller
public class MyController {

    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("msg","hello shiro");
        return "index.html";
    }

    @RequestMapping("user/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("user/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/userlogin")
    public String login(String username,String password,Model model){


        Subject subject = SecurityUtils.getSubject();

        // 封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        System.out.println(token);

        try {
            subject.login(token); // 执行登录方法，如果没有异常就说明ok了
            return "index";
        }catch (UnknownAccountException e){  // 用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){ // 密码不存在
            model.addAttribute("msg","密码不存在");
            return "login";
        }
    }
}
