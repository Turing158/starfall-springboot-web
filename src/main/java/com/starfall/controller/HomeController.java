package com.starfall.controller;

import com.starfall.Application;
import com.starfall.dao.SignInDao;
import com.starfall.service.HomeService;
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
    private HomeService homeService;
    @Autowired
    private SignInDao signInDao;

//进入主页与返回主页的控制
    @RequestMapping(value = {"/home","/"})
    public String home(
            HttpSession session
    ){
        homeService.enterHome(session);
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

