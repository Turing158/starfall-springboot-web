package com.starfall.service;

import com.starfall.dao.NoticeDao;
import com.starfall.entity.Exp;
import com.starfall.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class HomeService {
    @Autowired
    private NoticeDao noticeDao;

    public void enterHome(
            HttpSession session
    ){
        //        测试用
//        session.setMaxInactiveInterval(1);
        session.setMaxInactiveInterval(60*60);
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
    }
}
