package com.starfall.controller;

import com.mysql.cj.util.StringUtils;
import com.starfall.Application;
import com.starfall.dao.CommentDao;
import com.starfall.dao.NoticeDao;
import com.starfall.dao.TopicDao;
import com.starfall.entity.Comment;
import com.starfall.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.plugin.com.event.COMEventHandler;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
@SpringBootApplication(scanBasePackageClasses = Application.class)
public class TopicController {

    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private TopicDao topicDao;
    @Autowired
    private CommentDao commentDao;

    @RequestMapping("/topic")
    public String topic(
            HttpSession session,
            @RequestParam(value = "label",required = false) String label,
            @RequestParam(value = "page",required = false) String page
    ){
        int page_int = 0;
        int lastPage;
        if(!StringUtils.isNullOrEmpty(page)){
            page_int = Integer.parseInt(page)-1;
        }
        String label_Chinese = null;
        session.setAttribute("labelTF",false);
        session.setAttribute("label",null);
        if (!StringUtils.isNullOrEmpty(label)){
            session.setAttribute("labelTF",true);
            session.setAttribute("label","'"+label+"'");
            label_Chinese = label(label);
        }
        Pageable pageable =PageRequest.of(page_int,10, Sort.by("id").ascending());
        if (StringUtils.isNullOrEmpty(label)){
            lastPage = topicDao.countAllBy();
            session.setAttribute("topics",topicDao.findAll(pageable));
        }
        else {
            lastPage = topicDao.countAllByLabel(label_Chinese);
            session.setAttribute("topics",topicDao.findAllByLabel(label_Chinese,pageable));
        }
        lastPage = (lastPage+9)/10;
        session.setAttribute("page",page_int+1);
        session.setAttribute("lastPage",lastPage);
        session.setAttribute("noticeLength",noticeDao.count());
        session.setAttribute("notices",noticeDao.findAll());
        return "topic";
    }
    @RequestMapping("/topic/html")
    public String topicPage(
            HttpSession session,
            @RequestParam(value = "html",required = false) String html,
            @RequestParam(value = "page",required = false) String page,
            @RequestParam(value = "user",required = false) String user
    ){
        int page_int = 0;
        int html_int = 0;
        session.setAttribute("lookUser",null);
        if (!StringUtils.isNullOrEmpty(page)){
            page_int = Integer.parseInt(page)-1;
        }
        if(!StringUtils.isNullOrEmpty(html)){
            html_int = Integer.parseInt(html);
        }
        boolean topicTF = topicDao.findById((long) html_int).isPresent();
        Topic topic;
        session.setAttribute("commentPage",page_int+1);
        if (topicTF){
            int lastPage = 0;
            topic = topicDao.findById((long) html_int).get();
            session.setAttribute("topic",topic);
            session.setAttribute("topicContent",topic.getContent());
            session.setAttribute("html",html_int);
            Pageable pageable =PageRequest.of(page_int,5, Sort.by("id").ascending());
            if(StringUtils.isNullOrEmpty(user)){
                session.setAttribute("comments",commentDao.findAllByTopicid(pageable,html_int));
                lastPage = commentDao.countAllByTopicid(html_int);
            }
            else {
                session.setAttribute("comments",commentDao.findAllByTopicidAndUser(pageable,html_int,user));
                lastPage = commentDao.countAllByTopicidAndUser(html_int,user);
                session.setAttribute("lookUser","'"+user+"'");
            }
            lastPage=(lastPage+4)/5;
            session.setAttribute("commentPageNum",page_int+1+"/"+lastPage);
            session.setAttribute("commentLastPage",lastPage);
//            System.out.println(topic.getContent());
            return "topic/1";
        }
        return "topic/null";
    }
    @RequestMapping("/saveComment")
    public String saveComment(
            HttpSession session,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "code")String code
    ){
        int html = (int) session.getAttribute("html");
        int lastPage = 1;
        session.setAttribute("commentContent",content);
        session.setAttribute("commentTips","error");
        if(StringUtils.isNullOrEmpty(content) || content.length() < 10){
            session.setAttribute("commentTips","formatError");
        }
        else if(session.getAttribute("code").equals(code)){
            LocalDateTime ldt = LocalDateTime.now();
            String user = (String) session.getAttribute("user");
            String date = ldt.toLocalDate()+" "+ldt.getHour()+":"+ldt.getMinute()+":"+ldt.getSecond();
            commentDao.save(new Comment(content,date,user,html));
            commentDao.updateData();
            lastPage = commentDao.countAllByTopicid(html);
            lastPage=(lastPage+4)/5;
            session.setAttribute("commentTips","success");
        }
        return "redirect:/topic/html?html="+html+"&page="+lastPage;
    }
    public String label(String label){
        switch(label){
            case "serve": return "服务端";
            case "Client":return "客户端";
            case "video": return "视频";
            case "article":return "文章";
            case "plug_in":return "插件";
            case "notice": return "公告";
        }
        return "no";
    }
}
