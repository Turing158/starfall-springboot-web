package com.starfall.controller;

import com.starfall.dao.TopicDao;
import com.starfall.dao.UserDao;
import com.starfall.entity.Exp;
import com.starfall.entity.Page;
import com.starfall.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class PersonalController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private TopicDao topicDao;

    @RequestMapping("/personal")
    public String personal(
            HttpSession session,
            @RequestParam(value = "user",required = false) String user,
            @RequestParam(value = "page",required = false) String page_str
    ){
        //初始化页数
        int page = 1;
        if(page_str != null){
            page = Integer.parseInt(page_str);
        }
        User personal = userDao.findByUser(user);
//        若不存在该用户，则返回主页
        if(personal == null){
            return "redirect:/home";
        }
        Pageable pageable = PageRequest.of(page-1,10, Sort.by("date").descending());
//        初始化对象
        Page pageObj = new Page(page, (topicDao.countAllByUser(user)+9)/10);
        Exp exp = new Exp(personal.getLevel(),personal.getExp());
//        放入session
        session.setAttribute("personal",personal);
        session.setAttribute("personalExp",exp);
        session.setAttribute("personalTopics",topicDao.findAllByUser(pageable,user));
        session.setAttribute("personalTopicPage",pageObj);
        return "personal";
    }
}
