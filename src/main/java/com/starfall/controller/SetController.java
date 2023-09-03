package com.starfall.controller;

import com.starfall.Application;
import com.starfall.dao.CommentDao;
import com.starfall.dao.TopicDao;
import com.starfall.dao.UserDao;
import com.starfall.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private TopicDao topicDao;



    @RequestMapping("/set")
    public String Set(
            HttpSession session
    ){
//        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
//        UserService userService = context.getBean("userService", UserService.class);
//        HttpSession session = req.getSession();

        return "set";
    }

    @RequestMapping("/set/savePage")
    @ResponseBody
    public void savePage(
            HttpSession session,
            @RequestBody(required = false)String page
    ){
        session.setAttribute("setPage",page);
    }
    @RequestMapping("/set/clearTips")
    @ResponseBody
    public void clearTips(
            HttpSession session
    ){
        session.setAttribute("setTips",null);
    }

//设置头像暂时不修改为springMVC格式,此方法位于servlet/set_head.java上
    @RequestMapping("/set/head")
    public String setHead(
            HttpSession session,
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws IOException, ServletException {
//        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
//        UserService userService = context.getBean("userService", UserService.class);
//        DiscussService discussService = context.getBean("discussService", DiscussService.class);
//        HttpSession session = req.getSession();
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
        String user =((User) session.getAttribute("user")).getUser();
        String filename = sdf.format(date) + r.nextInt();
        //        获取文件后缀
        String fileType = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));
        //        设置存储位置

//        String path = getServletContext().getRealPath("head_img/");
        String path = Objects.requireNonNull(this.getClass().getClassLoader().getResource("templates/head_img/")).getPath();
//        写入保存的路径
        part.write(path+filename+fileType);
//        永久保存头像，避免服务器崩溃导致丢失
//        part.write(savePath+filename+fileType);
        userDao.setHead(user,filename+fileType);
//        更新数据库里头像的名字
        commentDao.updateData();
        topicDao.updateData();
//        刷新session里的头像
        User userObj = userDao.findByUser(user);
        userObj.setHead(filename+fileType);
        session.setAttribute("user",userObj);
        return "redirect:/set";
    }


    @RequestMapping("/set/information")
    public String setInformation(
            HttpSession session,
            HttpServletRequest req,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "introduce",required = false) String introduce,
            @RequestParam(value = "code",required = false) String code
    ) throws IOException {
//        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
//        UserService userService = context.getBean("userService", UserService.class);
//        HttpSession session = req.getSession();
        req.setCharacterEncoding("utf-8");
        String user = ((User) session.getAttribute("user")).getUser();
        if(Objects.equals(code,"")){
            session.setAttribute("setTips","验证码不能为空");
        }
        else if(!Objects.equals(code,session.getAttribute("code"))){
            session.setAttribute("setTips","验证码错误");
        }
        else if ((name != null || introduce != null )&& Objects.equals(code,session.getAttribute("code"))){
            session.setAttribute("setTips","信息修改成功");
            userDao.updateInformation(user,name,introduce);
            commentDao.updateData();
            topicDao.updateData();
        }
        session.setAttribute("code",null);
        return "redirect:/set";
    }



    @RequestMapping("/set/password")
    public String setPassword(
            HttpSession session,
            HttpServletRequest req,
            @RequestParam(value = "oldPassword",required = false) String oldPassword,
            @RequestParam(value = "newPassword",required = false) String newPassword,
            @RequestParam(value = "code",required = false) String code

    ) throws IOException {
        req.setCharacterEncoding("utf-8");
        User userObj = (User) session.getAttribute("user");
        String user = userObj.getUser();
        boolean flag = oldPassword.equals(userObj.getPassword());
        if(flag && Objects.equals(code,session.getAttribute("code"))){
            session.setAttribute("setTips","密码修改成功");
            userDao.updatePassword(user,newPassword);
            session.setAttribute("code",null);
        }
        else if(!Objects.equals(code,session.getAttribute("code"))){
            session.setAttribute("setTips","验证码错误");
        }
        else{
            session.setAttribute("setTips","原密码错误");
        }
        return "redirect:/set";
    }
}
