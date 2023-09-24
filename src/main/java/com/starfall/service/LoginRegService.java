package com.starfall.service;

import com.mysql.cj.util.StringUtils;
import com.starfall.dao.UserDao;
import com.starfall.entity.Exp;
import com.starfall.entity.User;
import com.starfall.util.GetCode;
import com.starfall.util.MailUtil;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Objects;

@Service
public class LoginRegService {
    @Autowired
    private UserDao userDao;

    public String confirmLogin(
            HttpSession session,
            String user,
            String password,
            String code
    ){
        //        code = "aaaa";
        session.setAttribute("userLogin",user);
        //判断用户是否存在，及获取用户密码方便判断
        String flag;
        try{
            flag = userDao.findByUser(user).getPassword();
        }catch (NullPointerException e){
            flag = "null";
        }
        //验证码为空
        if(Objects.equals(code, "")){
            session.setAttribute("tips","验证码不能为空");
            session.setAttribute("password",password);
        }
        //验证码错误[性能测试注释掉]
        else if(!Objects.equals(code,session.getAttribute("code"))){
            session.setAttribute("tips","验证码错误");
            session.setAttribute("password",password);

        }
        //用户不存在
        else if(Objects.equals(flag, "null")){
            session.setAttribute("tips","用户名不存在");

        }
        //密码错误
        else if(!Objects.equals(flag, password)){
            session.setAttribute("tips","密码错误!");

        }
        //成功[性能测试换第一个]
//        else if(Objects.equals(flag, password)){
        else if(Objects.equals(flag, password) && Objects.equals(code,session.getAttribute("code"))){
            User userObj = userDao.findByUser(user);
            Exp exp = new Exp(userObj.getLevel(),userObj.getExp());
            session.setAttribute("user",userObj);
            session.setAttribute("userExp",exp);
            session.removeAttribute("password");
            session.removeAttribute("userLogin");
            session.setAttribute("code",null);
            return "success";
        }
        return "error";
    }


    public String checkUser(String user){
        if(!user.matches("^[0-9a-zA-Z_]+$")){
            return "noChar";
        }
        if(userDao.countByUser(user) != 0){
            return "exist";
        }
        return "not exist";
    }


    public String checkEmail(
            @RequestBody(required = false) String email
    ){
        if(userDao.countByEmail(email) != 0){
            return "exist";
        }
        return "not exist";
    }



    public String reg_email(
            HttpSession session,
            String user,
            String password,
            String email,
            String code
    ) {
        GetCode getCode = new GetCode();
        MailUtil mail = new MailUtil();
        String email_code;
        session.setAttribute("reg_user", user);
        session.setAttribute("reg_password", password);
        session.setAttribute("reg_email", email);
        session.setAttribute("code_tips", " ");
        session.setAttribute("reg_notice", null);
        //判断用户邮箱是否存在
        boolean user_flag = false;
        boolean flag = false;
        if (userDao.countByUser(user) != 0) {
            user_flag = true;
        }
        if (userDao.countByEmail(email) != 0) {
            flag = true;
        }
        //原来已经成功进入验证页面，用于重新发送
        if ((boolean) session.getAttribute("enter_flag")) {
            email_code = getCode.getcode();
            session.setAttribute("email_code", email_code);
            try {
                mail.reg_mail(email, email_code);
            } catch (EmailException e) {
                e.printStackTrace();
            }
            session.setAttribute("code_tips", "邮箱已重新发送，请注意查收");
            return "successSendEmailAgain";
        } else {

            if (user.length() < 3) {
                session.setAttribute("reg_tips", "用户名不能小于3个字符");
            } else if (password.length() < 6) {
                session.setAttribute("reg_tips", "密码不能小于6个字符");
            } else if (!Objects.equals(code, session.getAttribute("code"))) {
                session.setAttribute("reg_tips", "验证码错误");
            } else if (user_flag) {
                session.setAttribute("reg_tips", "用户已存在");
            } else if (flag) {
                session.setAttribute("reg_tips", "邮箱已存在");
            }
            //成功
            else if (!flag && !user_flag && Objects.equals(code, session.getAttribute("code"))) {
                session.setAttribute("enter_flag", true);
                email_code = getCode.getcode();//获取验证码
                session.setAttribute("email_code", email_code);
                try {
                    mail.reg_mail(email, email_code);//发邮件
                } catch (EmailException e) {
                    e.printStackTrace();
                }
                session.setAttribute("code", null);
                return "successSendEmail";
            }
        }
        return "error";
    }

    public void reg(
            HttpSession session,
            @RequestParam(value = "reg_email_code",required = false)String email_code
    ){
        String user = (String) session.getAttribute("reg_user");
        String password = (String) session.getAttribute("reg_password");
        String email = (String) session.getAttribute("reg_email");
        if(email_code.isEmpty()){
            session.setAttribute("code_tips","验证码不能为空");
        }
        else if(!email_code.equals(session.getAttribute("email_code"))){
            session.setAttribute("code_tips","验证码错误，请仔细检查邮箱与验证码是否正确");
        }
        else if(email_code.equals(session.getAttribute("email_code"))){
            //创建新用户默认名字
            char[] id = session.getId().toCharArray();
            StringBuilder id_last6 = new StringBuilder();
            for (int i=0;i < 6;i++) {
                id_last6.insert(0,id[id.length-1-i]);
            }
            String name = "新用户"+ id_last6;
            LocalDate date = LocalDate.now();
            userDao.save(new User(user,password,String.valueOf(date),1,name,null,email,"null.jpg",0,0));
            session.setAttribute("reg_notice","block");
            session.removeAttribute("email_code");
            session.removeAttribute("reg_email");
            session.removeAttribute("reg_user");
            session.removeAttribute("reg_password");
            session.removeAttribute("reg_tips");
            session.removeAttribute("reg");
            session.removeAttribute("enter_flag");
            session.setAttribute("tips","成功注册账号");
        }
//        return "success";
    }

    public String forget_email(
            HttpSession session,
            String email,
            String code
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
                return "enterVerifyCode";
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
        return "error";
    }




    public String confirm_forget(
            HttpSession session,
            String emailCode
    ){
        if(StringUtils.isNullOrEmpty(emailCode)){
            emailCode = null;
        }
        System.out.println("DemailCode:"+session.getAttribute("forgetCode"));
        if(session.getAttribute("forgetCode").equals(emailCode)){
            session.setAttribute("forgetPass",true);
            String email = (String) session.getAttribute("forgetEmail");
            String user = userDao.findByEmail(email).getUser();
            session.setAttribute("forgetUser",user);
            session.setAttribute("forgetCode",null);
            return "success";
        }
        else if(Objects.equals(emailCode,null)){
            session.setAttribute("forget_code_tips","验证码为空！");
        }
        else {
            session.setAttribute("forget_code_tips","邮箱验证码错误，请检查！");
        }
        return "error";
    }



    public  String reset(
            HttpSession session,
            String password1,
            String password2
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
            return "error";
        }
        return "success";
    }
}
