package com.starfall.controller;

import com.starfall.dao.CommentDao;
import com.starfall.dao.NoticeDao;
import com.starfall.dao.TopicDao;
import com.starfall.dao.UserDao;
import com.starfall.entity.Comment;
import com.starfall.entity.Notice;
import com.starfall.entity.Topic;
import com.starfall.entity.User;
import com.starfall.util.OnlineUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.*;
import java.util.Objects;
import java.util.Optional;

@Controller
public class EditController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private TopicDao topicDao;
    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private CommentDao commentDao;

    @RequestMapping("/administer/html")
    public String toEdit(
            HttpSession session,
            @RequestParam(required = false,defaultValue = "1") int pageU,
            @RequestParam(required = false,defaultValue = "1") int pageT,
            @RequestParam(required = false,defaultValue = "1") int pageN,
            @RequestParam(required = false,defaultValue = "1") int pageC
    ){
        Pageable pageableU = PageRequest.of(pageU-1,20, Sort.by("user").ascending());
        Pageable pageableT = PageRequest.of(pageT-1,20, Sort.by("id").ascending());
        Pageable pageableN = PageRequest.of(pageN-1,20, Sort.by("id").ascending());
        Pageable pageableC = PageRequest.of(pageC-1,20, Sort.by("id").ascending());

        session.setAttribute("adminUsers",userDao.findAll(pageableU));
        session.setAttribute("adminPageUNum",pageU);
        session.setAttribute("adminPageULast",topicDao.count()/21+1);

        session.setAttribute("adminTopics",topicDao.findAll(pageableT));
        session.setAttribute("adminPageTNum",pageT);
        session.setAttribute("adminPageTLast",topicDao.count()/21+1);

        session.setAttribute("adminNotices",noticeDao.findAll(pageableN));
        session.setAttribute("adminPageNNum",pageN);
        session.setAttribute("adminPageNLast",noticeDao.count()/21+1);

        session.setAttribute("adminComments",commentDao.findAll(pageableC));
        session.setAttribute("adminPageCNum",pageC);
        session.setAttribute("adminPageCLast",commentDao.count()/21+1);

        return "administer/edit";
    }




//存储当前页面
    @RequestMapping("/administer/setPage")
    @ResponseBody
    public void setPage(
            HttpSession session,
            @RequestBody(required = false)String page
    ){
        session.setAttribute("administerPage",page);
    }
    @RequestMapping("/administer/getOnline")
    @ResponseBody
    public int getOnlineCount(
        HttpSession session
    ){
        return OnlineUtil.list.size();
    }


//进入修改页面
    @RequestMapping("/administer/modify")
    public String modify(
            HttpSession session,
            @RequestParam(required = false) String user,
            @RequestParam(required = false) String topic,
            @RequestParam(required = false) String notice,
            @RequestParam(required = false) String comment
    ){
        if(user != null){
            Optional<User> userObj = userDao.findById(user);
            if(userObj.isPresent()){
                session.setAttribute("administerModifyInfU",userObj.get());
                session.setAttribute("administerModify",userObj.get().getUser());
            }
            else {
                session.setAttribute("administerModify","not exist");
            }
        }
        else if(topic != null){
            Long topicId = (long) Integer.parseInt(topic);
            Optional<Topic> topicObj = topicDao.findById(topicId);
            if(topicObj.isPresent()){
                session.setAttribute("administerModifyInfT",topicObj.get());
                session.setAttribute("administerModify",topicObj.get().getId());
            }
            else {
                session.setAttribute("administerModify","not exist");
            }
        }
        else if(notice != null){
            Long noticeId = (long) Integer.parseInt(notice);
            Optional<Notice> noticeObj = noticeDao.findById(noticeId);
            if(noticeObj.isPresent()){
                session.setAttribute("administerModifyInfN",noticeObj.get());
                session.setAttribute("administerModify",noticeObj.get().getId());
            }
            else {
                session.setAttribute("administerModify","not exist");
            }
        }
        else if(comment != null){
            Long commentId = (long) Integer.parseInt(comment);
            Optional<Comment> commentObj = commentDao.findById(commentId);
            if(commentObj.isPresent()){
                session.setAttribute("administerModifyInfC",commentObj.get());
                session.setAttribute("administerModify",commentObj.get().getId());
            }
            else {
                session.setAttribute("administerModify","not exist");
            }

        }
        session.setAttribute("administerModify",null);
        return "administer/modify";
    }

//    删除功能
    @RequestMapping("/administer/delete")
    public String delete(
            HttpSession session,
            @RequestParam(required = false) String user,
            @RequestParam(required = false) String topic,
            @RequestParam(required = false) String notice,
            @RequestParam(required = false) String comment
    ){
        if(user != null){
            Optional<User> userObj = userDao.findById(user);
            if(userObj.isPresent()){
                userDao.deleteById(user);
                session.setAttribute("administerTips","删除成功！已删除User："+user);
            }
            else {
                session.setAttribute("administerModify","not exist");
            }
        }
        else if(topic != null){
            Long topicId = (long) Integer.parseInt(topic);
            Optional<Topic> topicObj = topicDao.findById(topicId);
            if(topicObj.isPresent()){
                topicDao.deleteById(topicId);
                session.setAttribute("administerTips","删除成功！已删除Topic："+topic);
            }
            else {
                session.setAttribute("administerModify","not exist");
            }
        }
        else if(notice != null){
            Long noticeId = (long) Integer.parseInt(notice);
            Optional<Notice> noticeObj = noticeDao.findById(noticeId);
            if(noticeObj.isPresent()){
                noticeDao.deleteById(noticeId);
                session.setAttribute("administerTips","删除成功！已删除Notice："+notice);
            }
            else {
                session.setAttribute("administerModify","not exist");
            }
        }
        else if(comment != null){
            Long commentId = (long) Integer.parseInt(comment);
            Optional<Comment> commentObj = commentDao.findById(commentId);
            if(commentObj.isPresent()){
                commentDao.deleteById(commentId);
                session.setAttribute("administerTips","删除成功！已删除Comment："+comment);
            }
            else {
                session.setAttribute("administerModify","not exist");
            }

        }
        session.setAttribute("administerModify",null);
        return "redirect:/administer/html";
    }


//    清除提示消息
    @RequestMapping("/administer/clearTips")
    @ResponseBody
    public void clearTips(
            HttpSession session
    ){
        session.setAttribute("administerTips",null);
    }



//  有关添加功能========================================================================================
//    添加数据页面
    @RequestMapping("/administer/addData")
    public String addDataPage(
            @RequestParam(required = false) String type
    ){
        if(!Objects.equals(type, "topic")){
            return "administer/addData";
        }
        return "administer/addTopic";
    }


//    添加用户
    @RequestMapping("/administer/addUserData")
    public String modifyUser(
            HttpSession session,
            @RequestParam(required = false,defaultValue = "null") String user,
            @RequestParam(required = false,defaultValue = "") String password,
            @RequestParam(required = false,defaultValue = "") String email,
            @RequestParam(required = false,defaultValue = "0") String level,
            @RequestParam(required = false,defaultValue = "") String date,
            @RequestParam(required = false,defaultValue = "") String name,
            @RequestParam(required = false,defaultValue = "") String head,
            @RequestParam(required = false,defaultValue = "0") String promise,
            @RequestParam(required = false,defaultValue = "") String introduce
    ){
        userDao.save(new User(user,password,date,Integer.parseInt(level),name,introduce,email,head,Integer.parseInt(promise)));
        session.setAttribute("administerTips","添加成功！已添加User："+user);
        return "redirect:/administer/html";
    }

    //  有关添加功能========================================================================================
}


