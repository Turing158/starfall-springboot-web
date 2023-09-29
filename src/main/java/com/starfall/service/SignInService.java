package com.starfall.service;

import com.starfall.dao.SignInDao;
import com.starfall.dao.UserDao;
import com.starfall.entity.SignIn;
import com.starfall.entity.User;
import com.starfall.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class SignInService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private SignInDao signInDao;

    public String SignIn(
            HttpSession session
    ){
        User user = (User) session.getAttribute("user");
        if(user == null){
            session.setAttribute("signInTips","error-noLogin");
            return "error";
        }
        List<SignIn> signIns = signInDao.findAllByUserOrderByDateDesc(user.getUser());
        LocalDateTime yesterday = LocalDateTime.parse(signIns.get(0).getDate());
        LocalDateTime today = LocalDateTime.now();
        if(!DateUtil.isSameDay(yesterday,today)){
            session.setAttribute("signInTips","error-alreadySignIn");
            return "error";
        }
        user.setSigncontinuous(user.getSigncontinuous()+1);
        if(!DateUtil.isConsecutiveDates(yesterday,today)){
            user.setSigncontinuous(0);
        }
        Random r = new Random();
        user.setExp(user.getExp()+r.nextInt(100)+80);
        userDao.save(user);
        signInDao.save(new SignIn());
        session.setAttribute("user",user);
        return "success";
    }

    public boolean isSignInToday(
            HttpSession session
    ){
        User user = (User) session.getAttribute("user");
        String date = "%"+LocalDateTime.now().toString().substring(0,10)+"%";
        signInDao.existsAllByUserAndDateLike(user.getUser(),date);
        return false;
    }
}
