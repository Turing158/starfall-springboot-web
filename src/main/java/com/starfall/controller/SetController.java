package com.starfall.controller;

import com.starfall.Application;
import com.starfall.dao.CommentDao;
import com.starfall.dao.TopicDao;
import com.starfall.dao.UserDao;
import com.starfall.service.SetService;
import com.starfall.util.OtherUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.io.IOException;


@Controller
@SpringBootApplication(scanBasePackageClasses = Application.class)
public class SetController extends HttpServlet {

    @Autowired
    private SetService setService;


    @RequestMapping("/set")
    public String Set(
            HttpSession session,
            @RequestParam(value = "page",required = false) String page_str
    ){
        String status = setService.enterSet(session,page_str);
        if(status.equals("success")) {
            return "set";
        }
        return "redirect:/home";
    }


//    保存设置的页面
    @RequestMapping("/set/savePage")
    @ResponseBody
    public void savePage(
            HttpSession session,
            @RequestBody(required = false)String page
    ){
        session.setAttribute("setPage",page);
    }


//    清理提示
    @RequestMapping("/set/clearTips")
    @ResponseBody
    public void clearTips(
            HttpSession session
    ){
        session.setAttribute("setTips",null);
        session.setAttribute("setErrorColor",null);
    }

//进入主题帖的编辑
    @RequestMapping("/topic/editTopic")
    public String editTopic(
            HttpSession session,
            @RequestParam(value = "id",required = false) String id_str
    ){
        String status = setService.editTopic(session,id_str);
        if(status.equals("success")) {
            return "/topic/setTopic";
        }
        else if(status.equals("error")){
            return "redirect:/set";
        }
        return "redirect:/home";
    }
//    保持编辑的主题帖
    @RequestMapping("/set/submitEditTopic")
    public String submitEditTopic(
            HttpSession session,
            @RequestParam(value = "verifyCode",required = false) String code,//输入的验证码
            @RequestParam(value = "bigTitle",required = false) String bigTitle,//大标题
            @RequestParam(value = "label",required = false) String label,//标签
            @RequestParam(value = "titleName",required = false) String titleName,//标题
            @RequestParam(value = "titleEnglishName",required = false) String titleEnglishName,//标题英文名
            @RequestParam(value = "source",required = false) String source,//来源
            @RequestParam(value = "version",required = false) String version,//版本
            @RequestParam(value = "authorName",required = false) String authorName,//作者
            @RequestParam(value = "language",required = false) String language,//语言
            @RequestParam(value = "address",required = false) String address,//地址
            @RequestParam(value = "download",required = false) String download,//下载地址
            @RequestParam(value = "content",required = false) String content//内容
    ){
        String status = setService.submitEditTopic(session,code,bigTitle,label,titleName,titleEnglishName,source,version,authorName,language,address,download,content);
        if(status.equals("error")||status.equals("success")){
            return "redirect:/set";
        }
        return "redirect:/topic/editTopic?id="+status;
    }
//    删除主题帖
    @RequestMapping("/set/deleteTopic")
    public String deleteTopic(
            HttpSession session,
            @RequestParam(value = "id",required = false) String id_str
    ){
        setService.deleteTopic(session,id_str);
        return "redirect:/set";
    }

//    设置头像
    @RequestMapping("/set/head")
    public String setHead(
            HttpSession session,
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws IOException, ServletException {
        setService.setHead(session,req,resp);
        return "redirect:/set";
    }


    @RequestMapping("/set/information")
    public String setInformation(
            HttpSession session,
            HttpServletRequest req,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "introduce",required = false) String introduce,
            @RequestParam(value = "code",required = false) String code
    ) throws IOException {
        setService.setInformation(session,req,name,introduce,code);
        return "redirect:/set";
    }



    @RequestMapping("/set/password")
    public String setPassword(
            HttpSession session,
            HttpServletRequest req,
            @RequestParam(value = "oldPassword",required = false) String oldPassword,
            @RequestParam(value = "newPassword",required = false) String newPassword,
            @RequestParam(value = "code",required = false) String code

    ) throws IOException {
        setService.setPassword(session,req,oldPassword,newPassword,code);
        return "redirect:/set";
    }
}
