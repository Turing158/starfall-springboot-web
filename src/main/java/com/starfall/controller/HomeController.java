package com.starfall.controller;

import com.starfall.Application;
import com.starfall.dao.DiscussDao;
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


@Controller
@SpringBootApplication(scanBasePackageClasses = Application.class)
public class HomeController{

    @Autowired
    private DiscussDao discussDao;
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = {"/home","/"})
    public String home(
            HttpSession session,
            @RequestParam(value = "only_user",required = false) String only_user,
            @RequestParam(value = "page",required = false) String page_str
    ) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
//        DiscussService discussService = context.getBean("discussService", DiscussService.class);
        if (Objects.equals(only_user,"null")){
            only_user = "";
        }
        session.setAttribute("only_user",only_user);
        Integer page = 1;
        session.setAttribute("page",1);
        if (!StringUtils.isEmpty(page_str)){
            page = Integer.valueOf(page_str);
        }
        int last_page = (discussDao.countAllBy()+4)/5;
        if(Objects.equals(only_user,"")){
            last_page = 1;
        }
        List<Discuss> discussList = new ArrayList<>();
        if(Objects.equals(only_user,"") || Objects.equals(only_user,null)){
            List<Discuss> discuss_data = discussDao.findAll();
            for (int i = page-1; i < page+4; i++) {
                discussList.add(discuss_data.get(i));
            }
        }
        else {
            last_page = (discussDao.countAllByUser(only_user)+4)/5;
            List<Discuss> discuss_data = discussDao.findAllByUser(only_user);
            for (int i = page-1; i < page+4; i++) {
                discussList.add(discuss_data.get(i));
            }
        }
        session.setAttribute("comment",discussList);
        session.setAttribute("last_page",last_page);
        session.setAttribute("page",page);
        session.setAttribute("page_center",page+"/"+last_page);
        if(page > last_page){
            session.setAttribute("page",page-1);
        }
        else if(page <= 0){
            session.setAttribute("page",1);
        }
        return "index";
    }

    @RequestMapping("/add_comment")
    public String addComment(
            HttpSession session,
            HttpServletRequest req,
            @RequestParam(value = "content",required = false)String content,
            @RequestParam(value = "comment_VerifyCode",required = false)String code
    ) throws UnsupportedEncodingException {
//        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
//        UserService userService = context.getBean("userService", UserService.class);
//        DiscussService discussService = context.getBean("discussService", DiscussService.class);
        LocalDateTime ldt = LocalDateTime.now();
        session.setAttribute("comment","block");
        session.setAttribute("home","none");
        req.setCharacterEncoding("utf-8");
        String date = LocalDate.now()+" "+ldt.getHour()+":"+ldt.getMinute()+":"+ldt.getSecond();
        String user = (String) session.getAttribute("user");
        String name = userDao.findByUser(user).getName();
        session.setAttribute("comment_input",content);
        if(Objects.equals(code,session.getAttribute("code"))){
//            discussService.addComment(user,content,date,name);
            discussDao.save(new Discuss(user,name,content,date,userDao.findByUser(user).getHead()));
//            discussService.updateHead();
            discussDao.updateHeadData();
            session.setAttribute("comment_tips","发话成功");
            session.setAttribute("comment_input",null);
            session.setAttribute("code",null);
        }
        else{
            session.setAttribute("comment_tips","验证码错误");
        }
        return "redirect:/home";
    }

    @RequestMapping("/exit")
    public String exit(
            HttpSession session
    ){
        session.invalidate();
        return "redirect:/home";
    }
}

