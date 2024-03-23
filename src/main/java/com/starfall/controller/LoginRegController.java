package com.starfall.controller;

import com.starfall.Application;
import com.starfall.service.LoginRegService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



//登录注册控制器
@Controller
@SpringBootApplication(scanBasePackageClasses = Application.class)
public class LoginRegController {


    @Autowired
    private LoginRegService loginRegService;

    //前往登录
    @RequestMapping("/login")
    public String toLogin(
            HttpSession session
    ){
        session.setAttribute("reg_notice",null);
        session.setAttribute("enter_flag",false);
        return "login";
    }
    //前往注册
    @RequestMapping("/reg")
    public String toReg(
            HttpSession session
    ){
        session.setAttribute("submit","button");
        return "reg";
    }

    @RequestMapping("/forget")
    public String toForget(
            HttpSession session
    ){
        session.setAttribute("forgetCodePass",false);
        session.setAttribute("forgetPass",false);
        return "forget";
    }


//确认登录
    @RequestMapping("/confirm_login")
    public String login(
            HttpSession session,
            @RequestParam(value = "user",required = false)String user,
            @RequestParam(value = "password",required = false)String password,
            @RequestParam(value = "login_code",required = false)String code
    ){
        String status = loginRegService.confirmLogin(session,user,password,code);
        if(status.equals("success")){
            return "redirect:/home";
        }
//        status = "error"执行下面
        return "redirect:/login";
    }


    @RequestMapping("/checkUser")
    @ResponseBody
    public String checkUser(
            @RequestBody(required = false) String user
    ){
        return loginRegService.checkUser(user);
    }

    @RequestMapping("/checkEmail")
    @ResponseBody
    public String checkEmail(
            @RequestBody(required = false) String email
    ){
        return loginRegService.checkEmail(email);
    }


//注册发邮件
    @RequestMapping("/verify_code")
    public String reg_email(
            HttpSession session,
            @RequestParam(value = "vreg_user",required = false)String user,
            @RequestParam(value = "vreg_password",required = false)String password,
            @RequestParam(value = "vreg_email",required = false)String email,
            @RequestParam(value = "vreg_code",required = false)String code
    ){
        String status = loginRegService.reg_email(session,user,password,email,code);
        if(status.equals("successSendEmail") || status.equals("successSendEmailAgain")){
            return "reg_emailCode";
        }
        return "reg";
    }


//验证邮箱验证码
    @RequestMapping("/confirm_reg")
    public String reg(
            HttpSession session,
            @RequestParam(value = "reg_email_code",required = false)String email_code
    ){
        loginRegService.reg(session,email_code);
        return "reg_emailCode";
    }

    //找回密码的邮箱验证
    @RequestMapping("/verify_Email")
    public String forget_email(
            HttpSession session,
            @RequestParam(value = "email",required = false) String email,
            @RequestParam(value = "code",required = false) String code
    ) throws MessagingException {
        String status = loginRegService.forget_email(session,email,code);
        if(status.equals("enterVerifyCode")){
            return "forget_emailCode";
        }
        return "forget";
    }

    @RequestMapping("/confirm_forget")
    public String confirm_forget(
            HttpSession session,
            @RequestParam(value = "emailCode",required = false) String emailCode
    ){
        String status = loginRegService.confirm_forget(session,emailCode);
        if(status.equals("success")){
            return "reset";
        }
        return "forget_emailCode";
    }
    @RequestMapping("/reset_password")
    public  String reset(
            HttpSession session,
            @RequestParam(value = "password",required = false) String password1,
            @RequestParam(value = "confirm_password",required = false) String password2
    ){
        String status = loginRegService.reset(session,password1,password2);
        if(status.equals("success")){
            return "redirect:/login";
        }
        return "reset";
    }
}
