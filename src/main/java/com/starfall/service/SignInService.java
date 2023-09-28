package com.starfall.service;

import com.starfall.dao.UserDao;
import com.starfall.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Service
public class SignInService {
    @Autowired
    private UserDao userDao;

    public String SignIn(
            HttpSession session
    ){
        User user = (User) session.getAttribute("user");
        if(user == null){
            session.setAttribute("signIn","error-noLogin");
            return "error";
        }
        if(user.getSignin() >= 1){
            session.setAttribute("signIn","error-alreadySign");
            return "error";
        }
        Random r = new Random();
        user.setExp(user.getExp()+r.nextInt(100)+80);
        user.setSignin(1);
        userDao.save(user);
        session.setAttribute("user",user);
        return "success";
    }
}
