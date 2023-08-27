package com.starfall.controller;

import com.fasterxml.jackson.core.JsonToken;
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
import java.util.List;
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




//    获取在线人数
    @RequestMapping("/administer/getOnline")
    @ResponseBody
    public int getOnlineCount(
        HttpSession session
    ){
        return OnlineUtil.list.size();
    }


//    有关修改功能===============================================================
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

    //    添加用户
    @RequestMapping("/administer/modifyUserData")
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
        User userObj = new User(user,password,date,Integer.parseInt(level),name,introduce,email,head,Integer.parseInt(promise));
        if(userDao.existsById(user)){
            session.setAttribute("administerTips","修改失败！已存在User："+user);
            session.setAttribute("administerModifyInfU",userObj);
            return "redirect:/administer/addData?type=user";
        }
        userDao.save(userObj);
        session.setAttribute("administerTips","修改成功！已修改User："+user);
        session.removeAttribute("administerModifyInfU");
        return "redirect:/administer/html";
    }

    //    添加话题
    @RequestMapping("/administer/modifyTopicData")
    public String modifyTopic(
            HttpSession session,
            @RequestParam(required = false,defaultValue = "0") String id,
            @RequestParam(required = false,defaultValue = "0") String view,
            @RequestParam(required = false,defaultValue = "0") String comment,
            @RequestParam(required = false,defaultValue = "") String label,
            @RequestParam(required = false,defaultValue = "") String bigTitle,
            @RequestParam(required = false,defaultValue = "1000-01-01") String date,
            @RequestParam(required = false,defaultValue = "") String user,
            @RequestParam(required = false,defaultValue = "") String titleName,
            @RequestParam(required = false,defaultValue = "") String titleEnglishName,
            @RequestParam(required = false,defaultValue = "") String source,
            @RequestParam(required = false,defaultValue = "") String version,
            @RequestParam(required = false,defaultValue = "") String authorName,
            @RequestParam(required = false,defaultValue = "") String language,
            @RequestParam(required = false,defaultValue = "") String address,
            @RequestParam(required = false,defaultValue = "") String download,
            @RequestParam(required = false,defaultValue = "") String content
    ){
        long idLong = Integer.parseInt(id);
        Topic topicObj = new Topic(idLong,"",label,bigTitle,user,date,Integer.parseInt(comment),Integer.parseInt(view),labelCE(label),titleName,titleEnglishName,source,version,language,address,download,content,authorName);
        if(topicDao.existsById(idLong)){
            session.setAttribute("administerTips","修改失败！已存在Topic："+id);
            session.setAttribute("administerModifyInfT",topicObj);
            return "redirect:/administer/addData?type=topic";
        }
//        如果id为0，说明是新添加的，需要重新设置id
        if(idLong == 0){
            List<Topic> topics = topicDao.findAll(Sort.by("id").descending());
            idLong = topics.get(0).getId()+1;
            topicObj.setId(idLong);
        }
        topicDao.save(topicObj);
        topicDao.updateData();
        session.setAttribute("administerTips","修改成功！已修改Topic："+idLong);
        session.removeAttribute("administerModifyInfT");
        return "redirect:/administer/html";
    }

    //    添加公告
    @RequestMapping("/administer/modifyNoticeData")
    public String modifyNotice(
            HttpSession session,
            @RequestParam(required = false,defaultValue = "0") String id,
            @RequestParam(required = false,defaultValue = "") String content
    ){
        long idLong = Integer.parseInt(id);
        Notice noticeObj = new Notice(idLong,content);
        if(noticeDao.existsById(idLong)){
            session.setAttribute("administerTips","修改失败！已存在Notice："+id);
            session.setAttribute("administerModifyInfN",noticeObj);
            return "redirect:/administer/addData?type=notice";
        }
//        如果id为0，说明是新添加的，需要重新设置id
        if(idLong == 0){
            List<Notice> notices = noticeDao.findAll(Sort.by("id").descending());
            idLong = notices.get(0).getId()+1;
            noticeObj.setId(idLong);
        }
        noticeDao.save(noticeObj);
        session.setAttribute("administerTips","修改成功！已修改公告："+idLong);
        session.removeAttribute("administerModifyInfN");
        return "redirect:/administer/html";
    }


    //    添加评论
    @RequestMapping("/administer/modifyCommentData")
    public String modifyComment(
            HttpSession session,
            @RequestParam(required = false,defaultValue = "0") String id,
            @RequestParam(required = false,defaultValue = "0") String topicid,
            @RequestParam(required = false,defaultValue = "") String user,
            @RequestParam(required = false,defaultValue = "") String date,
            @RequestParam(required = false,defaultValue = "") String content
    ){
        long idLong = Integer.parseInt(id);
        Comment commentObj = new Comment(idLong,content,date,user,Integer.parseInt(topicid));
        if(commentDao.existsById((long) Integer.parseInt(id))){
            session.setAttribute("administerTips","修改失败！已存在Comment："+id);
            session.setAttribute("administerModifyInfC",commentObj);
            return "redirect:/administer/addData?type=comment";
        }
//        如果id为0，说明是新添加的，需要重新设置id
        if(idLong == 0){
            List<Comment> comments = commentDao.findAll(Sort.by("id").descending());
            idLong = comments.get(0).getId()+1;
            commentObj.setId(idLong);
        }
        commentDao.save(commentObj);
        commentDao.updateData();
        session.setAttribute("administerTips","修改成功！已修改评论："+idLong);
        session.removeAttribute("administerModifyInfC");
        return "redirect:/administer/html";
    }

//        ==========================================================================
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
            HttpSession session,
            @RequestParam(required = false) String type
    ){
//        初始化session，不然无法访问，会使渲染层报空指针异常
        User user = new User();
        if (session.getAttribute("administerModifyInfU") == null){
            session.setAttribute("administerModifyInfU",user);
        }
        Topic topic = new Topic();
        if (session.getAttribute("administerModifyInfT") == null){
            session.setAttribute("administerModifyInfT",topic);
        }
        Notice notice = new Notice();
        if (session.getAttribute("administerModifyInfN") == null){
            session.setAttribute("administerModifyInfN",notice);
        }
        Comment comment = new Comment();
        if (session.getAttribute("administerModifyInfC") == null){
            session.setAttribute("administerModifyInfC",comment);
        }
//        判断类型
        if(!Objects.equals(type, "topic")){
            return "administer/addData";
        }
        return "administer/addTopic";
    }


//    添加用户
    @RequestMapping("/administer/addUserData")
    public String addUser(
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
        User userObj = new User(user,password,date,Integer.parseInt(level),name,introduce,email,head,Integer.parseInt(promise));
        if(userDao.existsById(user)){
            session.setAttribute("administerTips","添加失败！已存在User："+user);
            session.setAttribute("administerModifyInfU",userObj);
            return "redirect:/administer/addData?type=user";
        }
        userDao.save(userObj);
        session.setAttribute("administerTips","添加成功！已添加User："+user);
        session.removeAttribute("administerModifyInfU");
        return "redirect:/administer/html";
    }

//    添加话题
    @RequestMapping("/administer/addTopicData")
    public String addTopic(
            HttpSession session,
            @RequestParam(required = false,defaultValue = "0") String id,
            @RequestParam(required = false,defaultValue = "0") String view,
            @RequestParam(required = false,defaultValue = "0") String comment,
            @RequestParam(required = false,defaultValue = "") String label,
            @RequestParam(required = false,defaultValue = "") String bigTitle,
            @RequestParam(required = false,defaultValue = "1000-01-01") String date,
            @RequestParam(required = false,defaultValue = "") String user,
            @RequestParam(required = false,defaultValue = "") String titleName,
            @RequestParam(required = false,defaultValue = "") String titleEnglishName,
            @RequestParam(required = false,defaultValue = "") String source,
            @RequestParam(required = false,defaultValue = "") String version,
            @RequestParam(required = false,defaultValue = "") String authorName,
            @RequestParam(required = false,defaultValue = "") String language,
            @RequestParam(required = false,defaultValue = "") String address,
            @RequestParam(required = false,defaultValue = "") String download,
            @RequestParam(required = false,defaultValue = "") String content
    ){
        long idLong = Integer.parseInt(id);
        Topic topicObj = new Topic(idLong,"",label,bigTitle,user,date,Integer.parseInt(comment),Integer.parseInt(view),labelCE(label),titleName,titleEnglishName,source,version,language,address,download,content,authorName);
        if(topicDao.existsById(idLong)){
            session.setAttribute("administerTips","添加失败！已存在Topic："+id);
            session.setAttribute("administerModifyInfT",topicObj);
            return "redirect:/administer/addData?type=topic";
        }
//        如果id为0，说明是新添加的，需要重新设置id
        if(idLong == 0){
            List<Topic> topics = topicDao.findAll(Sort.by("id").descending());
            idLong = topics.get(0).getId()+1;
            topicObj.setId(idLong);
        }
        topicDao.save(topicObj);
        topicDao.updateData();
        session.setAttribute("administerTips","添加成功！已添加Topic："+idLong);
        session.removeAttribute("administerModifyInfT");
        return "redirect:/administer/html";
    }

//    添加公告
    @RequestMapping("/administer/addNoticeData")
    public String addNotice(
            HttpSession session,
            @RequestParam(required = false,defaultValue = "0") String id,
            @RequestParam(required = false,defaultValue = "") String content
    ){
        long idLong = Integer.parseInt(id);
        Notice noticeObj = new Notice(idLong,content);
        if(noticeDao.existsById(idLong)){
            session.setAttribute("administerTips","添加失败！已存在Notice："+id);
            session.setAttribute("administerModifyInfN",noticeObj);
            return "redirect:/administer/addData?type=notice";
        }
//        如果id为0，说明是新添加的，需要重新设置id
        if(idLong == 0){
            List<Notice> notices = noticeDao.findAll(Sort.by("id").descending());
            idLong = notices.get(0).getId()+1;
            noticeObj.setId(idLong);
        }
        noticeDao.save(noticeObj);
        session.setAttribute("administerTips","添加成功！已添加公告："+idLong);
        session.removeAttribute("administerModifyInfN");
        return "redirect:/administer/html";
    }


//    添加评论
    @RequestMapping("/administer/addCommentData")
    public String addComment(
            HttpSession session,
            @RequestParam(required = false,defaultValue = "0") String id,
            @RequestParam(required = false,defaultValue = "0") String topicid,
            @RequestParam(required = false,defaultValue = "") String user,
            @RequestParam(required = false,defaultValue = "") String date,
            @RequestParam(required = false,defaultValue = "") String content
    ){
        long idLong = Integer.parseInt(id);
        Comment commentObj = new Comment(idLong,content,date,user,Integer.parseInt(topicid));
        if(commentDao.existsById((long) Integer.parseInt(id))){
            session.setAttribute("administerTips","添加失败！已存在Comment："+id);
            session.setAttribute("administerModifyInfC",commentObj);
            return "redirect:/administer/addData?type=comment";
        }
//        如果id为0，说明是新添加的，需要重新设置id
        if(idLong == 0){
            List<Comment> comments = commentDao.findAll(Sort.by("id").descending());
            idLong = comments.get(0).getId()+1;
            commentObj.setId(idLong);
        }
        commentDao.save(commentObj);
        commentDao.updateData();
        session.setAttribute("administerTips","添加成功！已添加评论："+idLong);
        session.removeAttribute("administerModifyInfC");
        return "redirect:/administer/html";
    }

    //  有关添加功能========================================================================================


//处理label字符串
    public String labelEC(String label){
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
    public String labelCE(String label){
        switch(label){
            case "服务端": return "serve";
            case "客户端":return "Client";
            case "视频": return "video";
            case "文章":return "article";
            case "插件":return "plug_in";
            case "公告": return "notice";
        }
        return "no";
    }
}






