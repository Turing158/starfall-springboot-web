package com.starfall.controller;


import com.starfall.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class PersonalController {
    @Autowired
    private PersonalService personalService;


    @RequestMapping("/personal")
    public String personal(
            HttpSession session,
            @RequestParam(value = "user",required = false) String user,
            @RequestParam(value = "page",required = false) String page_str
    ) {
        String status = personalService.personal(session, user, page_str);
        if (status.equals("success")) {
            return "personal";
        }
        return "redirect:/home";
    }
}
