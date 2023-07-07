package com.starfall.controller;

import com.starfall.Application;
import com.starfall.dao.DiscussDao;
import com.starfall.dao.NoticeDao;
import com.starfall.dao.UserDao;
import com.starfall.entity.Discuss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


//主页控制器
@Controller
@SpringBootApplication(scanBasePackageClasses = Application.class)
public class HomeController{

    @Autowired
    private NoticeDao noticeDao;


//进入主页与返回主页的控制
    @RequestMapping(value = {"/home","/"})
    public String home(
            HttpSession session
    ) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
//        DiscussService discussService = context.getBean("discussService", DiscussService.class);
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

