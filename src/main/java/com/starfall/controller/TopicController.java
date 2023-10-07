package com.starfall.controller;

import com.starfall.Application;
import com.starfall.dao.CommentDao;
import com.starfall.dao.GoodDao;
import com.starfall.dao.NoticeDao;
import com.starfall.dao.TopicDao;
import com.starfall.service.TopicService;
import com.starfall.util.OtherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SpringBootApplication(scanBasePackageClasses = Application.class)
public class TopicController {

    @Autowired
    private TopicService topicService;

    //前往主题区
    @RequestMapping("/topic")
    public String topic(
            HttpSession session,
            @RequestParam(value = "label",required = false) String label,
            @RequestParam(value = "page",required = false) String page
    ){
        topicService.topic(session,label,page);
        return "topic";
    }

    //进入查看主题
    @RequestMapping("/topic/html")
    public String topicPage(
            HttpSession session,
            @RequestParam(value = "html",required = false) String html,
            @RequestParam(value = "page",required = false) String page,
            @RequestParam(value = "user",required = false) String user
    ){
        String status = topicService.topicPage(session,html,page,user);
        if(status.equals("success")){
            return "topic/1";
        }
        //防止找不到主题，直接返回 null.html
        return "topic/null";
    }

    //点赞
    @RequestMapping("/topic/like")
    public String like(
            HttpSession session
    ){
        int status = topicService.like(session);
        return "redirect:/topic/html?html="+status;
    }
    @RequestMapping("/topic/dislike")
    public String dislike(
            HttpSession session
    ){
        int status = topicService.dislike(session);
        return "redirect:/topic/html?html="+status;
    }
    @RequestMapping("/topic/cancelLike")
    public String cancelLike(
            HttpSession session
    ){
        int status = topicService.cancelLike(session);
        return "redirect:/topic/html?html="+status;
    }

    //发布评论
    @RequestMapping("/saveComment")
    public String saveComment(
            HttpSession session,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "code")String code
    ){
        List<Integer> status = topicService.saveComment(session,content,code);
        if(status.size() <= 1){
            return "redirect:/topic/html?html="+status.get(0);
        }
        return "redirect:/topic/html?html="+status.get(0)+"&page="+status.get(1);
    }
    //进入编辑主题
    @RequestMapping("/topic/publish")
    public String publish(
            HttpSession session
    ){
        String status = topicService.publish(session);
        if(status.equals("error")){
            return "redirect:/topic";
        }
//        return "topic/edit";
//        调试时注释掉下面，使用时不要注释
        else if(status.equals("success")){
            return "topic/edit";
        }
        return "topic/noEdit";
    }
    //发布主题
    @RequestMapping("/topic/submitTopic")
    public String submitTopic(
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
        String status = topicService.submitTopic(session,code,bigTitle,label,titleName,titleEnglishName,source,version,authorName,language,address,download,content);
        if(status.equals("error") || status.equals("error-noEntire")){
            return "redirect:/topic/publish";

        }
        return "redirect:/topic/html?html="+status;
    }
    //    清除session
    @RequestMapping("/topic/clearTips")
    @ResponseBody
    public void clearTips(
            HttpSession session
    ){
        session.setAttribute("editErrorColor",null);
        session.setAttribute("editErrorTips",null);
        session.setAttribute("searchTips",null);
    }

    @RequestMapping("/topic/search")
    public String search(
            HttpSession session,
            @RequestParam(required = false,defaultValue = "all") String select,
            @RequestParam(required = false) String search,
            @RequestParam(value = "page",required = false) String page_str
    ) {
        topicService.search(session,search,select,page_str);
        return "topic/search";
    }

}
