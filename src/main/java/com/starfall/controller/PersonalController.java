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
        int page = 1;
        if(page_str != null){
            page = Integer.parseInt(page_str);
        }
        User personal = userDao.findByUser(user);
        if(personal == null){
            return "redirect:/home";
        }
        Pageable pageable = PageRequest.of(page-1,10, Sort.by("date").descending());
        Page pageObj = new Page(page, (topicDao.countAllByUser(user)+9)/10);
        Exp exp = new Exp(personal.getLevel(),personal.getExp());
        session.setAttribute("personal",personal);
        session.setAttribute("personalExp",exp);
        session.setAttribute("personalTopics",topicDao.findAllByUser(pageable,user));
        session.setAttribute("personalTopicPage",pageObj);
        return "personal";
    }
}
