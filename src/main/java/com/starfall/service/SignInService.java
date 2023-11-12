package com.starfall.service;

import com.starfall.dao.SignInDao;
import com.starfall.dao.UserDao;
import com.starfall.entity.Exp;
import com.starfall.entity.SignIn;
import com.starfall.entity.User;
import com.starfall.util.DateUtil;
import com.starfall.util.OtherUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
public class SignInService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private SignInDao signInDao;


    public void enterSignIn(
            HttpSession session
    ){
        User userObj = (User) session.getAttribute("user");
        if(session.getAttribute("userExp") == null){
            Exp exp = new Exp(userObj.getLevel(),userObj.getExp());
            session.setAttribute("userExp",exp);
        }
        session.setAttribute("signInHistories",signInDao.findAllByUserOrderByDateDesc(userObj.getUser()));
    }

    public String SignIn(
            HttpSession session
    ){
        User user = (User) session.getAttribute("user");
        if(user == null){
            session.setAttribute("signInTips","error-noLogin");
            return "error";
        }
        List<SignIn> signIns = signInDao.findAllByUserOrderByDateDesc(user.getUser());
//        此判断为了防止用户第一次签到时，数据库中没有数据，导致程序出错
        if(!signIns.isEmpty()) {
            LocalDateTime yesterday = LocalDateTime.parse(signIns.get(0).getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDateTime today = LocalDateTime.now();
            if (DateUtil.isSameDay(yesterday, today)) {
                session.setAttribute("signInTips", "error-alreadySignIn");
                return "error";
            }
            user.setSigncontinuous(user.getSigncontinuous() + 1);
            if (!DateUtil.isConsecutiveDates(yesterday, today)) {
                user.setSigncontinuous(0);
            }
        }
//        用户第一次签到时，数据库中没有数据，导致数据出问题
        if(signIns.isEmpty()){
            user.setSigncontinuous(1);
        }
        Random r = new Random();
        int getExp = r.nextInt(100)+80;
        user.setExp(user.getExp()+getExp);
        user.setLevel(OtherUtil.isLevel(user.getExp()));
        userDao.save(user);
        signInDao.save(new SignIn(signInDao.findAll(Sort.by(Sort.Direction.DESC, "id")).get(0).getId()+1,user.getUser(),LocalDateTime.now().toString(),"获得经验"+getExp+"点",null));
        signInDao.updateData();
        Exp exp = new Exp(user.getLevel(),user.getExp());
        session.setAttribute("userExp",exp);
        session.setAttribute("user",user);
        return "success";
    }

    public boolean isSignInToday(
            HttpSession session
    ){
        User user = (User) session.getAttribute("user");
        String date = "%"+LocalDateTime.now().toString().substring(0,10)+"%";
        return signInDao.existsAllByUserAndDateLike(user.getUser(),date);
    }
}
