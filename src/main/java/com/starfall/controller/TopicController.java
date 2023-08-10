package com.starfall.controller;

import com.mysql.cj.util.StringUtils;
import com.starfall.Application;
import com.starfall.dao.CommentDao;
import com.starfall.dao.NoticeDao;
import com.starfall.dao.TopicDao;
import com.starfall.dao.UserDao;
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

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Objects;

@Controller
@SpringBootApplication(scanBasePackageClasses = Application.class)
public class TopicController {

    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private TopicDao topicDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private UserDao userDao;

    //前往主题区
    @RequestMapping("/topic")
    public String topic(
            HttpSession session,
            @RequestParam(value = "label",required = false) String label,
            @RequestParam(value = "page",required = false) String page
    ){
        int page_int = 0;
        int lastPage;
        //当page不为空指针
        if(!StringUtils.isNullOrEmpty(page)){
            page_int = Integer.parseInt(page)-1;
        }
        String label_Chinese = null;
        session.setAttribute("labelTF",false);//初始化
        session.setAttribute("label",null);//session的label控制js的，详细看topic文件js
        //当label不为空指针
        if (!StringUtils.isNullOrEmpty(label)){
            session.setAttribute("labelTF",true);//控制"显示全部"按钮
            session.setAttribute("label","'"+label+"'");//防止session输出时，字符串没''引号
            label_Chinese = label(label);
        }
        //分页
        Pageable pageable =PageRequest.of(page_int,10, Sort.by("id").ascending());

        //如果label为空指针，就查全部，否，就查只包含此label的主题
        if (StringUtils.isNullOrEmpty(label)){

            //查全部
            lastPage = topicDao.countAllBy();
            session.setAttribute("topics",topicDao.findAll(pageable));
        }
        else {

            //查只是这个label的主题
            lastPage = topicDao.countAllByLabel(label_Chinese);
            session.setAttribute("topics",topicDao.findAllByLabel(label_Chinese,pageable));
        }
        //最后一页数字的处理
        lastPage = (lastPage+9)/10;
        //初始化session第几页
        session.setAttribute("page",page_int+1);
        //初始化session最后一页
        session.setAttribute("lastPage",lastPage);
        //初始化session，公告的长度
        session.setAttribute("noticeLength",noticeDao.count());
        //初始化session，公告的内容
        session.setAttribute("notices",noticeDao.findAll());
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
        int page_int = 0;
        int html_int = 0;
        //初始化session，只看ta
        session.setAttribute("lookUser",null);
        //当page不为空时
        if (!StringUtils.isNullOrEmpty(page)){
            page_int = Integer.parseInt(page)-1;
        }
        //当html不为空时
        if(!StringUtils.isNullOrEmpty(html)){
            html_int = Integer.parseInt(html);
        }
        //为了判断是否存在此数据
        boolean topicTF = topicDao.findById((long) html_int).isPresent();
        Topic topic;
        //初始化session，第几页
        session.setAttribute("commentPage",page_int+1);
        if (topicTF){
            int lastPage;
            //获取对应主题
            topic = topicDao.findById((long) html_int).get();
            //初始化session，主题的信息
            session.setAttribute("topic",topic);
            session.setAttribute("topicContent",topic.getContent());

            //设置进入了哪个主题
            session.setAttribute("html",html_int);

            //评论区分页
            Pageable pageable =PageRequest.of(page_int,5, Sort.by("id").ascending());
            //如果输入的只看ta为空，查全部评论，否，查只看ta
            if(StringUtils.isNullOrEmpty(user)){
                //查全部
                session.setAttribute("comments",commentDao.findAllByTopicid(pageable,html_int));
                lastPage = commentDao.countAllByTopicid(html_int);
            }
            else {
                //查只看ta
                session.setAttribute("comments",commentDao.findAllByTopicidAndUser(pageable,html_int,user));
                lastPage = commentDao.countAllByTopicidAndUser(html_int,user);
                //防止输出的字符串不加引号
                session.setAttribute("lookUser","'"+user+"'");
            }
            //处理最后一页数字
            lastPage=(lastPage+4)/5;


            //初始化session，页数展示
            session.setAttribute("commentPageNum",page_int+1+"/"+lastPage);
            session.setAttribute("commentLastPage",lastPage);
            return "topic/1";
        }
        //防止找不到主题，直接返回 null.html
        return "topic/null";
    }


    //发布评论
    @RequestMapping("/saveComment")
    public String saveComment(
            HttpSession session,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "code")String code
    ){
        //获取你是在哪个主题发布的评论
        int html = (int) session.getAttribute("html");
        int lastPage = 1;

        //保存发布的内容
        session.setAttribute("commentContent",content);
        //初始化发布信息提示
        session.setAttribute("commentTips","error");


        //如果发布的是空内容，或者内容少于20个字，提示字数不够
        if(StringUtils.isNullOrEmpty(content) || content.length() < 10){
            session.setAttribute("commentTips","formatError");
        }
        //检测验证码是否正确
        else if(session.getAttribute("code").equals(code)){

            //获取时间
            LocalDateTime ldt = LocalDateTime.now();
            //获取用户
            String user = (String) session.getAttribute("user");
            //获取调整时间
            String date = ldt.toLocalDate()+" "+ldt.getHour()+":"+ldt.getMinute()+":"+ldt.getSecond();
            //保存评论
            commentDao.save(new Comment(content,date,user,html));
            //更新评论其他信息
            commentDao.updateData();

            //为了直接跳转最后一页
            lastPage = commentDao.countAllByTopicid(html);
            lastPage=(lastPage+4)/5;
            //发布提示成功
            session.setAttribute("commentTips","success");
        }
        return "redirect:/topic/html?html="+html+"&page="+lastPage;
    }

    @RequestMapping("/topic/publish")
    public String publish(
            HttpSession session
    ){
        if(!Objects.equals(session.getAttribute("promise"),null)){
            int promise = (int) session.getAttribute("promise");
            if(promise == 10){
                return "topic/edit";
            }
        }
        return "topic/noEdit";
    }


    //处理label字符串
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
