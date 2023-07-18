package com.starfall.controller;

import com.mysql.cj.util.StringUtils;
import com.starfall.Application;
import com.starfall.dao.NoticeDao;
import com.starfall.dao.TopicDao;
import com.starfall.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@SpringBootApplication(scanBasePackageClasses = Application.class)
public class TopicController {

    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private TopicDao topicDao;

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
        @RequestParam(value = "page",required = false) String page
    ){
        int page_int = 1;
        int html_int = 1;
        if (!StringUtils.isNullOrEmpty(page)){
            page_int = Integer.parseInt(page);
        }
        if(!StringUtils.isNullOrEmpty(html)){
            html_int = Integer.parseInt(html);
        }
        boolean topicTF = topicDao.findById((long) html_int).isPresent();
        Topic topic;
        if (topicTF){
            topic = topicDao.findById((long) html_int).get();
            session.setAttribute("topic",topic);
            session.setAttribute("topicContent",topic.getContent());
//            System.out.println(topic.getContent());
            return "topic/1";
        }
        return "topic/null";
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
