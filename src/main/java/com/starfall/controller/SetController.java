package com.starfall.controller;

import com.starfall.Application;
import com.starfall.dao.CommentDao;
import com.starfall.dao.UserDao;
import com.starfall.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@MultipartConfig
@Controller
@SpringBootApplication(scanBasePackageClasses = Application.class)
public class SetController extends HttpServlet {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CommentDao commentDao;



    @RequestMapping("/set")
    public String Set(
            HttpSession session
    ){
//        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
//        UserService userService = context.getBean("userService", UserService.class);
//        HttpSession session = req.getSession();
        if (session.getAttribute("display_me") == null){
            session.setAttribute("display_me","block");
            session.setAttribute("display_i","none");
            session.setAttribute("display_p","none");
            session.setAttribute("display_h","none");
        }
        String user_session = (String) session.getAttribute("user");
        User user = userDao.findByUser(user_session);
        session.setAttribute("name",user.getName());
        session.setAttribute("date",user.getDate());
        session.setAttribute("introduce",user.getIntroduce());
        session.setAttribute("email",user.getEmail());
        session.setAttribute("level",user.getLevel());
        return "set";
    }




//设置头像暂时不修改为springMVC格式,此方法位于servlet/set_head.java上
    @RequestMapping("/upload_head")
    public String setHead(
            HttpSession session,
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws IOException, ServletException {
//        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
//        UserService userService = context.getBean("userService", UserService.class);
//        DiscussService discussService = context.getBean("discussService", DiscussService.class);
//        HttpSession session = req.getSession();
        session.setAttribute("display_me","none");
        session.setAttribute("display_i","none");
        session.setAttribute("display_p","none");
        session.setAttribute("display_h","block");
//        改编码，以免乱码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        Random r = new Random();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss") ;
//        直接获取input的name的值
        Part part = req.getPart("head_img");
//        这个是保存头像的，现在保存只能临时的，这个填存服务器头像的存储位置，可永久保存
//        String savePath = "D:/data/head_img";
        String user = (String) session.getAttribute("user");
        String filename = sdf.format(date) + r.nextInt();
        //        获取文件后缀
        String fileType = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));
        //        设置存储位置

//        String path = getServletContext().getRealPath("head_img/");
        String path = Objects.requireNonNull(this.getClass().getClassLoader().getResource("static/head_img/")).getPath();
//        写入保存的路径
        part.write(path+filename+fileType);
//        永久保存头像，避免服务器崩溃导致丢失
//        part.write(savePath+filename+fileType);
        userDao.setHead(user,filename+fileType);
//        更新数据库里头像的名字
        commentDao.updateData();
        session.setAttribute("head",filename+fileType);
        return "redirect:/set";
    }


    @RequestMapping("/set_information")
    public String setInformation(
            HttpSession session,
            HttpServletRequest req,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "introduce",required = false) String introduce,
            @RequestParam(value = "seti_code",required = false) String code
    ) throws IOException {
//        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
//        UserService userService = context.getBean("userService", UserService.class);
//        HttpSession session = req.getSession();
        session.setAttribute("display_me","none");
        session.setAttribute("display_p","none");
        session.setAttribute("display_i","block");
        req.setCharacterEncoding("utf-8");
        String user = (String) session.getAttribute("user");
//        String name = req.getParameter("name");
//        String introduce = req.getParameter("introduce");
//        String code = req.getParameter("seti_code");
        if(Objects.equals(code,"")){
            session.setAttribute("i_tips","验证码不能为空");
        }
        else if(!Objects.equals(code,session.getAttribute("code"))){
            session.setAttribute("i_tips","验证码错误");
        }
        else if ((name != null || introduce != null )&& Objects.equals(code,session.getAttribute("code"))){
            session.setAttribute("i_tips","信息修改成功");
            userDao.updateInformation(user,name,introduce);
            commentDao.updateData();
        }
        session.setAttribute("code",null);
        return "redirect:/set";
    }



    @RequestMapping("/set_password")
    public String setPassword(
            HttpSession session,
            HttpServletRequest req,
            @RequestParam(value = "old_password",required = false) String old_password,
            @RequestParam(value = "new_password",required = false) String new_password,
            @RequestParam(value = "set_VerifyCode",required = false) String code

    ) throws IOException {
//        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
//        UserService userService = context.getBean("userService", UserService.class);
//        HttpSession session = req.getSession();
        req.setCharacterEncoding("utf-8");
        String user = (String) session.getAttribute("user");
//        String old_password = req.getParameter("old_password");
//        String new_password = req.getParameter("new_password");
//        String code = req.getParameter("seti_VerifyCode");
        session.setAttribute("display_me","none");
        session.setAttribute("display_p","block");
        session.setAttribute("display_i","none");
        boolean flag = old_password.equals(userDao.findByUser(user).getPassword());
        if(flag && Objects.equals(code,session.getAttribute("code"))){
            session.setAttribute("p_tips","密码修改成功");
            session.setAttribute("p_tips_color","lightgreen");
            userDao.updatePassword(user,new_password);
            session.setAttribute("code",null);
        }
        else if(!Objects.equals(code,session.getAttribute("code"))){
            session.setAttribute("p_tips","验证码错误");
            session.setAttribute("p_tips_color","rgb(255, 125, 125)");
        }
        else{
            session.setAttribute("p_tips","原密码错误");
            session.setAttribute("p_tips_color","rgb(255, 125, 125)");
        }
        return "redirect:/set";
    }
}
