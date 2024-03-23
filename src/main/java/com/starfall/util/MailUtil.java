package com.starfall.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


@Component
public class MailUtil{

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    public void sendMail(String mail,String title,String msg) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(mail);
            helper.setSubject(title);
            helper.setText(msg, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
//注册发送邮箱内容
    public void reg_mail(String mail,String num) throws MessagingException {
        String title = "StarFall注册验证码";
        String msg = "您好！\r\n    感谢注册此网站，也感谢你的大力支持\n注意：如您并未正在注册，请勿轻信任何索要验证码的坏人\n<center>↓↓↓↓您的注册验证码↓↓↓↓</center>\n<center style='font-size:40px;border:1px solid black;'>"+num.toUpperCase()+"</center>\n<center>↑↑↑↑您的注册验证码↑↑↑↑</center>";
        sendMail(mail,title,msg);
    }
//    修改密码发送邮箱内容
    public void set_mail(String mail,String num) {
        String title = "StarFall修改密码验证码";
        String msg = "您好！\r\n    感谢注册此网站，也感谢你的大力支持\n注意：如您并未忘记密码，请勿轻信任何索要验证码的坏人\n<center>↓↓↓↓您的设置验证码↓↓↓↓</center>\n<center style='font-size:40px'>"+num.toUpperCase()+"</center>\n<center>↑↑↑↑您的设置验证码↑↑↑↑</center>";
        sendMail(mail,title,msg);
    }

}
