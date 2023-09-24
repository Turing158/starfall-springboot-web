package com.starfall.service;

import com.starfall.dao.TopicDao;
import com.starfall.dao.UserDao;
import com.starfall.entity.Exp;
import com.starfall.entity.Page;
import com.starfall.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpSession;

@Service
public class PersonalService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private TopicDao topicDao;

    public String personal(
            HttpSession session,
            String user,
            String page_str
    ){
        //初始化页数
        int page = 1;
        if(page_str != null){
            page = Integer.parseInt(page_str);
        }
        User personal = userDao.findByUser(user);
//        若不存在该用户，则返回主页
        if(personal == null){
            return "error";
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
        return "success";
    }
}
