package com.starfall.controller;

import com.starfall.Application;
import com.starfall.dao.GoodDao;
import com.starfall.dao.NoticeDao;
import com.starfall.entity.Exp;
import com.starfall.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


//主页控制器
@Controller
@SpringBootApplication(scanBasePackageClasses = Application.class)
public class HomeController{

    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private GoodDao goodDao;


//进入主页与返回主页的控制
    @RequestMapping(value = {"/home","/"})
    public String home(
            HttpSession session
    ) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
//        DiscussService discussService = context.getBean("discussService", DiscussService.class);
//        测试用
//        session.setMaxInactiveInterval(1);
        session.setMaxInactiveInterval(10);
        if(session.getAttribute("user") != null){
            session.setMaxInactiveInterval(60*60*24*7);
            if(session.getAttribute("userExp") == null){
                User userObj = (User) session.getAttribute("user");
                Exp exp = new Exp(userObj.getLevel(),userObj.getExp());
                session.setAttribute("userExp",exp);
            }
        }
        session.setAttribute("noticeLength",noticeDao.count());
        session.setAttribute("notices",noticeDao.findAll());
        return "index";
    }
    //退出，将session清空
    @RequestMapping("/exit")
    public String exit(
            HttpSession session
    ){
        session.invalidate();
        return "redirect:/home";
    }



}

