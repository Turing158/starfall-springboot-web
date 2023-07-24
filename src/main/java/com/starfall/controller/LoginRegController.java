package com.starfall.controller;

import com.mysql.cj.util.StringUtils;
import com.starfall.Application;
import com.starfall.dao.UserDao;
import com.starfall.entity.User;
import com.starfall.util.GetCode;
import com.starfall.util.MailUtil;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Objects;


//登录注册控制器
@Controller
@SpringBootApplication(scanBasePackageClasses = Application.class)
public class LoginRegController {

    @Autowired
    private UserDao userDao;


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
//        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
//        UserService userService = context.getBean("userService", UserService.class);
//        HttpSession session = req.getSession();
//        String user = req.getParameter("user");
//        String password = req.getParameter("password");
//        String code = req.getParameter("login_code");
        session.setAttribute("user",user);
        //判断用户是否存在，及获取用户密码方便判断
        String flag;
        try{
            flag = userDao.findByUser(user).getPassword();
        }catch (NullPointerException e){
            flag = "null";
        }
        session.setAttribute("tips"," ");
        //验证码为空
        if(Objects.equals(code, "")){
            session.setAttribute("tips","验证码不能为空");
            session.setAttribute("user",user);
            session.setAttribute("password",password);
        }
        //验证码错误
        else if(!Objects.equals(code,session.getAttribute("code"))){
            session.setAttribute("tips","验证码错误");
            session.setAttribute("user",user);
            session.setAttribute("password",password);

        }
        //用户不存在
        else if(Objects.equals(flag, "null")){
            session.setAttribute("tips","用户名不存在");

        }
        //密码错误
        else if(!Objects.equals(flag, password)){
            session.setAttribute("tips","密码错误!");
            session.setAttribute("user",user);

        }
        //成功
        else if(Objects.equals(flag, password) && Objects.equals(code,session.getAttribute("code"))){
            String name = userDao.findByUser(user).getName();
            session.setAttribute("user",user);
            session.setAttribute("head",userDao.findByUser(user).getHead());
            session.setAttribute("password",password);
            session.setAttribute("login","1");
            session.setAttribute("introduce",userDao.findByUser(user).getIntroduce());
            session.setAttribute("name",name);
            session.setAttribute("code",null);
            return "redirect:/home";
        }
        return "redirect:/login";
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
//        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
//        UserService userService = context.getBean("userService", UserService.class);
//        HttpSession session = req.getSession();
        GetCode getCode = new GetCode();
        MailUtil mail = new MailUtil();
        String email_code;
//        String user = req.getParameter("vreg_user");
//        String password = req.getParameter("vreg_password");
//        String email = req.getParameter("vreg_email");
//        String code = req.getParameter("vreg_code");
        session.setAttribute("reg_user",user);
        session.setAttribute("reg_password",password);
        session.setAttribute("reg_email",email);
        session.setAttribute("code_tips"," ");
        session.setAttribute("reg_notice",null);
        //判断用户邮箱是否存在
        boolean user_flag = false;
        boolean flag = false;
        if(userDao.countByUser(user) != 0){
            user_flag = true;
        }
        if(userDao.countByEmail(email) != 0){
            flag = true;
        }
        //原来已经成功进入验证页面，用于重新发送
        if ((boolean) session.getAttribute("enter_flag")){
            email_code = getCode.getcode();
            session.setAttribute("email_code",email_code);
            try {
                mail.reg_mail(email,email_code);
            } catch (EmailException e) {
                e.printStackTrace();
            }
            session.setAttribute("code_tips","邮箱已重新发送，请注意查收");
            return "reg_emailCode";
        }
        else{

            if(user.length() < 3){
                session.setAttribute("reg_tips","用户名不能小于3个字符");
            }
            else if(password.length() < 6){
                session.setAttribute("reg_tips","密码不能小于6个字符");
            }
            else if (!Objects.equals(code,session.getAttribute("code"))){
                session.setAttribute("reg_tips","验证码错误");
            }
            else if(user_flag){
                session.setAttribute("reg_tips","用户已存在");
            }
            else if(flag){
                session.setAttribute("reg_tips","邮箱已存在");
            }
            //成功
            else if(!flag && !user_flag && Objects.equals(code,session.getAttribute("code"))){
                session.setAttribute("enter_flag",true);
                email_code = getCode.getcode();//获取验证码
                session.setAttribute("email_code",email_code);
                try {
                    mail.reg_mail(email,email_code);//发邮件
                } catch (EmailException e) {
                    e.printStackTrace();
                }
                session.setAttribute("code",null);
                return "reg_emailCode";
            }
        }

        return "reg";
    }



//验证邮箱验证码
    @RequestMapping("/confirm_reg")
    public String reg(
            HttpSession session,
            @RequestParam(value = "reg_email_code",required = false)String email_code
    ){
//        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
//        UserService userService = context.getBean("userService", UserService.class);
//        HttpSession session = req.getSession();
//        session.setAttribute("reg","block");
        String user = (String) session.getAttribute("reg_user");
        String password = (String) session.getAttribute("reg_password");
        String email = (String) session.getAttribute("reg_email");
//        String email_code = req.getParameter("reg_email_code");
        if(email_code.equals("")){
            session.setAttribute("code_tips","验证码不能为空");
        }
        else if(!email_code.equals(session.getAttribute("email_code"))){
            session.setAttribute("code_tips","验证码错误，请仔细检查邮箱与验证码是否正确");
        }
        else if(email_code.equals(session.getAttribute("email_code"))){

            char[] id = session.getId().toCharArray();
            StringBuilder id_last6 = new StringBuilder();
            for (int i=0;i < 6;i++) {
                id_last6.insert(0,id[id.length-1-i]);
            }
            String name = "新用户"+ id_last6;
            LocalDate date = LocalDate.now();
//            userService.reg(user,password,name,String.valueOf(date),email);
            userDao.save(new User(user,password,String.valueOf(date),1,name,null,email,"null.jpg"));
            session.setAttribute("reg_notice","block");
//            super.processTemplate("reg_emailCode",req,resp);
            session.setAttribute("email_code",null);
            session.setAttribute("reg_email",null);
            session.setAttribute("reg_user",null);
            session.setAttribute("reg_password",null);
            session.setAttribute("reg_tips",null);
            session.setAttribute("reg",null);
            session.setAttribute("tips","成功注册账号");
        }
        return "reg_emailCode";
    }

    //找回密码的邮箱验证
    @RequestMapping("/verify_Email")
    public String forget_email(
            HttpSession session,
            @RequestParam(value = "email",required = false) String email,
            @RequestParam(value = "code",required = false) String code
    ) throws EmailException {
        String emailCode;
        session.setAttribute("forgetEmail",email);
        if(StringUtils.isNullOrEmpty(code)){
            code = null;
        }
        if(session.getAttribute("forgetCodePass").equals(true)){
            session.setAttribute("forgetTips","已重新发送邮箱，请注意查收！");
        }
        if(session.getAttribute("code").equals(code) || session.getAttribute("forgetCodePass").equals(true)){
            if(userDao.countByEmail(email) != 0){
                GetCode getCode = new GetCode();
                MailUtil mail = new MailUtil();
                emailCode = getCode.getcode();
                session.setAttribute("forgetCode",emailCode);
                session.setAttribute("forgetCodePass",true);//为了重新发送邮件跳过验证码的session，也作为安保作用
                mail.set_mail(email,emailCode);
                return "forget_emailCode";
            }
            else {
                session.setAttribute("forgetTips","不存在该邮箱");
            }
        }
        else if(Objects.equals(code, null)){
            session.setAttribute("forgetTips","验证码不能为空");

        }
        else {
            session.setAttribute("forgetTips","验证码错误");
        }
        return "forget";
    }

    @RequestMapping("/confirm_forget")
    public String confirm_forget(
            HttpSession session,
            @RequestParam(value = "emailCode",required = false) String emailCode
    ){
        if(StringUtils.isNullOrEmpty(emailCode)){
            emailCode = null;
        }
        if(session.getAttribute("forgetCode").equals(emailCode)){
            session.setAttribute("forgetPass",true);
            String email = (String) session.getAttribute("forgetEmail");
            String user = userDao.findByEmail(email).getUser();
            session.setAttribute("forgetUser",user);
            session.setAttribute("forgetCode",null);
            return "reset";
        }
        else if(Objects.equals(emailCode,null)){
            session.setAttribute("forget_code_tips","验证码为空！");
        }
        else {
            session.setAttribute("forget_code_tips","邮箱验证码错误，请检查！");

        }
        return "forget_emailCode";
    }
    @RequestMapping("/reset_password")
    public  String reset(
            HttpSession session,
            @RequestParam(value = "password",required = false) String password1,
            @RequestParam(value = "confirm_password",required = false) String password2
    ){
        if(StringUtils.isNullOrEmpty(password1)){
            password1 = "false";
        }
        if(StringUtils.isNullOrEmpty(password2)){
            password2 = "true";
        }
        if(session.getAttribute("forgetPass").equals(true) && password1.equals(password2)) {
            String user = (String) session.getAttribute("forgetUser");
            userDao.updatePassword(user,password2);
            session.setAttribute("tips","成功修改密码");
        }
        else{
            session.setAttribute("forgetPass",false);
            return "reset";
        }
        return "redirect:/login";
    }
}
