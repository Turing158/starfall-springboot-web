package com.starfall.controller;

import com.starfall.entity.User;
import com.starfall.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SignInController {
    @Autowired
    private SignInService signInService;

    @RequestMapping("/signIn")
    public String toSignIn(
            HttpSession session
    ){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/home";
        }
        return "sign_in";
    }

    @RequestMapping("/signIn/sign")
    public String SignIn(
            HttpSession session
    ){
        signInService.SignIn(session);
        return "redirect:/signIn";
    }
}
