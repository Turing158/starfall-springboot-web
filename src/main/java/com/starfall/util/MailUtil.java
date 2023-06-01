package com.starfall.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;


public class MailUtil {
    public void mail(String mail,String title,String msg) throws EmailException{
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.qq.com");//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
        email.setCharset("UTF-8");
        email.setFrom("icethestral@vip.qq.com", "StarFall", "utf-8");
        email.setSSLOnConnect(true);//设置ssl连接
        email.addTo(mail);// 收件地址
        email.setAuthentication("icethestral@vip.qq.com", "fbgubsatkcwxjddf");//此处填写邮箱地址和客户端授权码
        email.setSubject(title);//此处填写邮件名，邮件名可任意填写
        email.setMsg(msg);//此处填写邮件内容
        email.send();
    }
    public void reg_mail(String mail,String num) throws EmailException {
        String title = "StarFall注册验证码";
        String msg = "您好！\r\n    感谢注册此网站，也感谢你的大力支持\n注意：如您并未正在注册，请勿轻信任何索要验证码的坏人\n<center>↓↓↓↓您的注册验证码↓↓↓↓</center>\n<center style='font-size:40px;border:1px solid black;'>"+num.toUpperCase()+"</center>\n<center>↑↑↑↑您的注册验证码↑↑↑↑</center>";
        mail(mail,title,msg);
    }
    public void set_mail(String mail,String num) throws EmailException {
        String title = "StarFall设置验证码";
        String msg = "您好！\r\n    感谢注册此网站，也感谢你的大力支持\n注意：如您并未正在修改密码，请勿轻信任何索要验证码的坏人\n<center>↓↓↓↓您的设置验证码↓↓↓↓</center>\n<center style='font-size:40px'>"+num.toUpperCase()+"</center>\n<center>↑↑↑↑您的设置验证码↑↑↑↑</center>";
        mail(mail,title,msg);
    }

}
