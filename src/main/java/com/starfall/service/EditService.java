package com.starfall.service;

import com.starfall.dao.*;
import com.starfall.entity.*;
import com.starfall.util.OtherUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;



import java.util.List;

import java.util.Optional;

@Service
public class EditService {


    @Autowired
    private UserDao userDao;
    @Autowired
    private TopicDao topicDao;
    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private GoodDao goodDao;
    private final OtherUtil otherUtil = new OtherUtil();

    //    进入编辑页面
    public String toEdit(
            HttpSession session,
            int pageU,
            int pageT,
            int pageN,
            int pageC,
            int pageG
    ){
        if(session.getAttribute("user") == null){
            return "error";
        }
        int promise = ((User) session.getAttribute("user")).getPromise();
        if(promise != 100){
            return "error";
        }
        Pageable pageableU = PageRequest.of(pageU-1,20, Sort.by("user").ascending());
        Pageable pageableT = PageRequest.of(pageT-1,20, Sort.by("id").ascending());
        Pageable pageableN = PageRequest.of(pageN-1,20, Sort.by("id").ascending());
        Pageable pageableC = PageRequest.of(pageC-1,20, Sort.by("id").ascending());
        Pageable pageableG = PageRequest.of(pageG-1,20, Sort.by("id").ascending());

        session.setAttribute("adminUsers",userDao.findAll(pageableU));
        Page pageUObj = new Page(pageU, (int) ((userDao.count()+19)/20));
        session.setAttribute("adminPageUsers",pageUObj);

        session.setAttribute("adminTopics",topicDao.findAll(pageableT));
        Page pageTObj = new Page(pageT, (int) ((topicDao.count()+19)/20));
        session.setAttribute("adminPageTopics",pageTObj);

        session.setAttribute("adminNotices",noticeDao.findAll(pageableN));
        Page pageNObj = new Page(pageN, (int) ((noticeDao.count()+19)/20));
        session.setAttribute("adminPageNotices",pageNObj);

        session.setAttribute("adminComments",commentDao.findAll(pageableC));
        Page pageCObj = new Page(pageC, (int) ((commentDao.count()+19)/20));
        session.setAttribute("adminPageComments",pageCObj);

        session.setAttribute("adminGoods", goodDao.findAll(pageableG));
        Page pageGObj = new Page(pageG, (int) ((goodDao.count()+19)/20));
        session.setAttribute("adminPageGoods",pageGObj);
        session.removeAttribute("administerModifyInfU");
        session.removeAttribute("administerModifyInT");
        session.removeAttribute("administerModifyInfN");
        session.removeAttribute("administerModifyInfC");
        session.removeAttribute("administerModifyInfG");
        return "success";
    }

    //    有关修改功能===============================================================
//进入修改页面
    public String modify(
            HttpSession session,
            String user,
            String topic,
            String notice,
            String comment,
            String good
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
                return "modifyTopic";
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
        else if(good != null){
            Long goodId = (long) Integer.parseInt(good);
            Optional<Good> goodObj = goodDao.findById(goodId);
            if(goodObj.isPresent()){
                session.setAttribute("administerModifyInfG",goodObj.get());
                session.setAttribute("administerModify",goodObj.get().getId());
            }
            else {
                session.setAttribute("administerModify","not exist");
            }

        }
        session.setAttribute("administerModify",null);
        return "modify";
    }

    //    添加用户
    public void modifyUser(
            HttpSession session,
            String user,
            String password,
            String email,
            String level,
            String date,
            String name,
            String head,
            String promise,
            String introduce,
            String exp_str,
            String signcountinuous_str
    ){
        int signcountinuous = Integer.parseInt(signcountinuous_str);
        int exp = Integer.parseInt(exp_str);
        User userObj = new User(user,password,date,Integer.parseInt(level),name,introduce,email,head,Integer.parseInt(promise),exp,signcountinuous);
        userDao.deleteById(((User) session.getAttribute("administerModifyInfU")).getUser());
        userDao.save(userObj);
        session.setAttribute("administerTips","修改成功！已修改User："+user);
        session.removeAttribute("administerModifyInfU");
    }

    //    添加话题
    public int modifyTopic(
            HttpSession session,
            String id,
            String view,
            String comment,
            String label,
            String bigTitle,
            String date,
            String user,
            String titleName,
            String titleEnglishName,
            String source,
            String version,
            String authorName,
            String language,
            String address,
            String download,
            String content
    ){
        long idLong = Integer.parseInt(id);
        long oldId = ((Topic) session.getAttribute("administerModifyInfT")).getId();
        Topic topicObj = new Topic(idLong,"",label,bigTitle,user,date,Integer.parseInt(comment),Integer.parseInt(view),otherUtil.labelCE(label),titleName,titleEnglishName,source,version,language,address,download,content,authorName);
        if(topicDao.existsById(idLong) && oldId != idLong){
            session.setAttribute("administerTips","修改失败！已存在Topic："+id);
            session.setAttribute("administerModifyInfT",topicObj);
            return (int) oldId;
        }
//        如果id为0，说明是新添加的，需要重新设置id
        if(idLong == 0){
            List<Topic> topics = topicDao.findAll(Sort.by("id").descending());
            idLong = topics.get(0).getId()+1;
            topicObj.setId(idLong);
        }
        topicDao.deleteById(oldId);
        topicDao.save(topicObj);
        topicDao.updateData();
        session.setAttribute("administerTips","修改成功！已修改Topic："+idLong);
        session.removeAttribute("administerModifyInfT");
        return -1;
    }

    //    添加公告
    public int modifyNotice(
            HttpSession session,
            String id,
            String content
    ){
        long idLong = Integer.parseInt(id);
        long oldId = ((Notice) session.getAttribute("administerModifyInfN")).getId();
        Notice noticeObj = new Notice(idLong,content);
        if(noticeDao.existsById(idLong) && oldId != idLong){
            session.setAttribute("administerTips","修改失败！已存在Notice："+id);
            session.setAttribute("administerModifyInfN",noticeObj);
            return (int) oldId;
        }
//        如果id为0，说明是新添加的，需要重新设置id
        if(idLong == 0){
            List<Notice> notices = noticeDao.findAll(Sort.by("id").descending());
            idLong = notices.get(0).getId()+1;
            noticeObj.setId(idLong);
        }
        noticeDao.save(noticeObj);
        noticeDao.deleteById(oldId);
        session.setAttribute("administerTips","修改成功！已修改公告："+idLong);
        session.removeAttribute("administerModifyInfN");
        return -1;
    }


    //    添加评论
    public int modifyComment(
            HttpSession session,
            String id,
            String topicid,
            String user,
            String date,
            String content
    ){
        long idLong = Integer.parseInt(id);
        long oldId = ((Comment) session.getAttribute("administerModifyInfC")).getId();
        Comment commentObj = new Comment(idLong,content,date,user,Integer.parseInt(topicid));
        if(commentDao.existsById(idLong) && oldId != idLong){
            session.setAttribute("administerTips","修改失败！已存在Comment："+id);
            session.setAttribute("administerModifyInfC",commentObj);
            return (int) oldId;
        }
//        如果id为0，说明是新添加的，需要重新设置id
        if(idLong == 0){
            List<Comment> comments = commentDao.findAll(Sort.by("id").descending());
            idLong = comments.get(0).getId()+1;
            commentObj.setId(idLong);
        }
        commentDao.deleteById(oldId);
        commentDao.save(commentObj);
        commentDao.updateData();
        session.setAttribute("administerTips","修改成功！已修改评论："+idLong);
        session.removeAttribute("administerModifyInfC");
        return -1;
    }

    //    添加评论
    public int modifyGood(
            HttpSession session,
            String id,
            String topicid,
            String user,
            String date,
            String good
    ){
        int goodInt = Integer.parseInt(good);
        long idLong = Integer.parseInt(id);
        long oldId = ((Good) session.getAttribute("administerModifyInfG")).getId();
        Good goodObj = new Good(idLong,goodInt,user,Integer.parseInt(topicid),date);
        if(goodDao.existsById(idLong) && oldId != idLong){
            session.setAttribute("administerTips","修改失败！已存在Good："+id);
            session.setAttribute("administerModifyInfG", goodObj);
            return (int) oldId;
        }
//        如果id为0，说明是新添加的，需要重新设置id
        if(idLong == 0){
            List<Good> goods = goodDao.findAll(Sort.by("id").descending());
            idLong = goods.get(0).getId()+1;
            goodObj.setId(idLong);
        }
        goodDao.deleteById(oldId);
        goodDao.save(goodObj);
        session.setAttribute("administerTips","修改成功！已修改喜欢："+idLong);
        session.removeAttribute("administerModifyInfG");
        return -1;
    }


    //        ==========================================================================
//    删除功能
    public void delete(
            HttpSession session,
            String user,
            String topic,
            String notice,
            String comment,
            String good
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
        else if(good != null){
            Long goodId = (long) Integer.parseInt(good);
            Optional<Good> goodObj = goodDao.findById(goodId);
            if(goodObj.isPresent()){
                goodDao.deleteById(goodId);
                session.setAttribute("administerTips","删除成功！已删除good："+good);
            }
            else {
                session.setAttribute("administerModify","not exist");
            }

        }
        session.setAttribute("administerModify",null);
    }
    //  有关添加功能========================================================================================
//    添加数据页面
    public String addDataPage(
            HttpSession session,
            String type
    ){
        if(type == null){
            return "error";
        }
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
        Good good = new Good();
        if (session.getAttribute("administerModifyInfG") == null){
            session.setAttribute("administerModifyInfG", good);
        }
//        判断类型
        if(type.equals("topic")){
            return "topic";
        }
        return "data";
    }
    //    添加用户
    public String addUser(
            HttpSession session,
            String user,
            String password,
            String email,
            String level,
            String date,
            String name,
            String head,
            String promise,
            String introduce,
            String exp_str,
            String signcountinuous_str
    ){
        int signcountinuous = Integer.parseInt(signcountinuous_str);
        int exp = Integer.parseInt(exp_str);
        User userObj = new User(user,password,date,Integer.parseInt(level),name,introduce,email,head,Integer.parseInt(promise),exp,signcountinuous);
        if(userDao.existsById(user)){
            session.setAttribute("administerTips","添加失败！已存在User："+user);
            session.setAttribute("administerModifyInfU",userObj);
            return "error";
        }
        userDao.save(userObj);
        session.setAttribute("administerTips","添加成功！已添加User："+user);
        session.removeAttribute("administerModifyInfU");
        return "success";
    }
    //    添加话题
    public String addTopic(
            HttpSession session,
            String id,
            String view,
            String comment,
            String label,
            String bigTitle,
            String date,
            String user,
            String titleName,
            String titleEnglishName,
            String source,
            String version,
            String authorName,
            String language,
            String address,
            String download,
            String content
    ){
        long idLong = Integer.parseInt(id);
        Topic topicObj = new Topic(idLong,"",label,bigTitle,user,date,Integer.parseInt(comment),Integer.parseInt(view),otherUtil.labelCE(label),titleName,titleEnglishName,source,version,language,address,download,content,authorName);
        if(topicDao.existsById(idLong)){
            session.setAttribute("administerTips","添加失败！已存在Topic："+id);
            session.setAttribute("administerModifyInfT",topicObj);
            return "error";
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
        return "success";
    }
    //    添加公告
    public String addNotice(
            HttpSession session,
            String id,
            String content
    ){
        long idLong = Integer.parseInt(id);
        Notice noticeObj = new Notice(idLong,content);
        if(noticeDao.existsById(idLong)){
            session.setAttribute("administerTips","添加失败！已存在Notice："+id);
            session.setAttribute("administerModifyInfN",noticeObj);
            return "error";
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
        return "success";
    }
    //    添加评论
    public String addComment(
            HttpSession session,
            String id,
            String topicid,
            String user,
            String date,
            String content
    ){
        long idLong = Integer.parseInt(id);
        Comment commentObj = new Comment(idLong,content,date,user,Integer.parseInt(topicid));
        if(commentDao.existsById((long) Integer.parseInt(id))){
            session.setAttribute("administerTips","添加失败！已存在Comment："+id);
            session.setAttribute("administerModifyInfC",commentObj);
            return "error";
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
        return "success";
    }
    //    添加喜欢
    public String addGood(
            HttpSession session,
            String id,
            String topicid,
            String user,
            String date,
            String good
    ){
        long idLong = Integer.parseInt(id);
        int goodInt = Integer.parseInt(good);
        Good goodObj = new Good(idLong,goodInt,user,Integer.parseInt(topicid),date);
        if(goodDao.existsById((long) Integer.parseInt(id))){
            session.setAttribute("administerTips","添加失败！已存在good："+id);
            session.setAttribute("administerModifyInfG", goodObj);
            return "error";
        }
//        如果id为0，说明是新添加的，需要重新设置id
        if(idLong == 0){
            List<Good> goods = goodDao.findAll(Sort.by("id").descending());
            idLong = goods.get(0).getId()+1;
            goodObj.setId(idLong);
        }
        goodDao.save(goodObj);
        session.setAttribute("administerTips","添加成功！已添加喜欢："+idLong);
        session.removeAttribute("administerModifyInfG");
        return "success";
    }

    //  有关添加功能========================================================================================
}
