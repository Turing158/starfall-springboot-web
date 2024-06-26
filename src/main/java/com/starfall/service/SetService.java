package com.starfall.service;

import com.starfall.dao.CommentDao;
import com.starfall.dao.TopicDao;
import com.starfall.dao.UserDao;
import com.starfall.entity.Exp;
import com.starfall.entity.Page;
import com.starfall.entity.Topic;
import com.starfall.entity.User;
import com.starfall.util.OtherUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Service
@MultipartConfig
public class SetService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private TopicDao topicDao;
    private final OtherUtil otherUtil = new OtherUtil();


    public String enterSet(
            HttpSession session,
            String page_str
    ){
        //        判断是否登录,防止错乱
        if(session.getAttribute("user") == null){
            return "error";
        }
        int page = 1;
        if(page_str != null){
            page = Integer.parseInt(page_str);
        }
        User userObj = (User) session.getAttribute("user");
        int lastPage = (topicDao.countAllByUser(userObj.getUser())+9)/10;
        Page pageObj = new Page(page,lastPage);
        Pageable pageable = PageRequest.of(page-1,10, Sort.by("date").descending());
        session.setAttribute("userTopic",topicDao.findAllByUser(pageable,userObj.getUser()));
        session.setAttribute("userTopicPage",pageObj);
//        防止session去除
        if(session.getAttribute("userExp") == null){
            Exp exp = new Exp(userObj.getLevel(),userObj.getExp());
            session.setAttribute("userExp",exp);
        }
        return "success";
    }


    public String editTopic(
            HttpSession session,
            String id_str
    ){
        //        判断是否登录,防止错乱
        if(session.getAttribute("user") == null){
            return "error-home";
        }
        long id = 0;
        User userObj = (User) session.getAttribute("user");
        if(id_str != null){
            id =Integer.parseInt(id_str);
        }
        if(id == 0){
//            防止直接输入网址进入编辑页面
            session.setAttribute("setTips","你想干嘛");
            return "error";
        }
        if(session.getAttribute("setTips") == null){
            Topic topicObj = topicDao.findAllById(id);
            if(!topicObj.getUser().equals(userObj.getUser())){
                session.setAttribute("setTips","你想干嘛!这不是你的东西");
                return "redirect:/set";
            }
            session.setAttribute("editTopic",topicObj);
        }
        return "success";
    }



    public String submitEditTopic(
            HttpSession session,
            String code,//输入的验证码
            String bigTitle,//大标题
            String label,//标签
            String titleName,//标题
            String titleEnglishName,//标题英文名
            String source,//来源
            String version,//版本
            String authorName,//作者
            String language,//语言
            String address,//地址
            String download,//下载地址
            String content//内容
    ){
        //        判断是否登录,防止错乱
        if(session.getAttribute("user") == null){
            return "error";
        }
        //防止label和source为空
        if(label == null || label.equals("请选择")){
            label = "";
        }
        if(source == null || source.equals("请选择")){
            source = "";
        }
        //方便检测是否有空值
        String[] list = {bigTitle,label,titleName,titleEnglishName,source,version,authorName,language,address,download,content};
        boolean infoNull = false;
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null || list[i].isEmpty()) {
                infoNull = true;
                break;
            }
        }
        Topic topicObj = (Topic) session.getAttribute("editTopic");
//        设置新内容
        topicObj.setTitle(bigTitle);
        topicObj.setLabel(label);
        topicObj.setTitlename(titleName);
        topicObj.setTitleenglishname(titleEnglishName);
        topicObj.setSource(source);
        topicObj.setVersion(version);
        topicObj.setAuthorname(authorName);
        topicObj.setLanguage(language);
        topicObj.setAddress(address);
        topicObj.setDownload(download);
        topicObj.setContent(content);
        //有空值，提示，并作修改
        if(infoNull){
            session.setAttribute("setErrorColor","border-color: darkred");
            session.setAttribute("setTips","请填写完整信息，必填已标红");
        }
        //没有空值，检测验证码，验证码为空
        else if(code.isEmpty()){
            session.setAttribute("setTips","验证码不能为空");
        }
        else if(session.getAttribute("code").equals(code)){
            //获取labelHref变量[详细看数据库]
            String labelHref = otherUtil.labelCE(label);
            //获取时间
            LocalDateTime ldt = LocalDateTime.now();
            String date = ldt.toLocalDate().toString();
//            设置些参数
            topicObj.setLabelHref(labelHref);
            topicObj.setDate(date);
            //添加主题
            topicDao.save(topicObj);
            //更新主题一些与user相关的信息
            topicDao.updateData();
            //清除session
            session.removeAttribute("editTopic");
            session.removeAttribute("setErrorColor");
            session.setAttribute("setTips","修改成功Topic:"+topicObj.getId());
            return "success";
        }
        else{
            session.setAttribute("setTips","验证码错误");
        }
        session.setAttribute("editTopic",topicObj);
        return topicObj.getId().toString();
    }




    public String deleteTopic(
            HttpSession session,
            String id_str
    ){
        //        判断是否登录,防止错乱
        if(session.getAttribute("user") == null){
            return "error";
        }
        long id = 0;
        User userObj = (User) session.getAttribute("user");
        if(id_str != null){
            id =Integer.parseInt(id_str);
        }
        if(id == 0){
//            防止直接输入网址删除主题帖
            session.setAttribute("setTips","你想干嘛");
        }
        Topic topicObj = topicDao.findAllById(id);
        if(!topicObj.getUser().equals(userObj.getUser())){
            session.setAttribute("setTips","你想干嘛!这不是你的东西");
        }
        topicDao.deleteById(topicObj.getId());
        session.setAttribute("setTips","删除成功");
        return "success";
    }


    public String setHead(
            HttpSession session,
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws IOException, ServletException {
        //        判断是否登录,防止错乱
        if(session.getAttribute("user") == null){
            return "error";
        }
//        改编码，以免乱码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        Random r = new Random();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss") ;
//        直接获取input的name的值
        Part part = req.getPart("head_img");
//        这个是保存头像的，现在保存只能临时的，这个填存服务器头像的存储位置，可永久保存
//        String savePath = "D:/data/head_img";
        String user =((User) session.getAttribute("user")).getUser();
        String filename = sdf.format(date) + r.nextInt();
        //        获取文件后缀
        String fileType = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));
        //        设置存储位置

//        String path = getServletContext().getRealPath("head_img/");
        String path = Objects.requireNonNull(this.getClass().getClassLoader().getResource("templates/head_img/")).getPath();
//        写入保存的路径
        part.write(path+filename+fileType);
//        永久保存头像，避免服务器崩溃导致丢失
//        part.write(savePath+filename+fileType);
        userDao.setHead(user,filename+fileType);
//        更新数据库里头像的名字
        commentDao.updateData();
        topicDao.updateData();
//        刷新session里的头像
        User userObj = userDao.findByUser(user);
        userObj.setHead(filename+fileType);
        session.setAttribute("user",userObj);
        session.setAttribute("setTips","头像修改成功");
        return "success";
    }





    public String setInformation(
            HttpSession session,
            HttpServletRequest req,
            String name,
            String introduce,
            String code
    ) throws IOException {
        //        判断是否登录,防止错乱
        if(session.getAttribute("user") == null){
            return "error";
        }
        req.setCharacterEncoding("utf-8");
        String user = ((User) session.getAttribute("user")).getUser();
        if(Objects.equals(code,"")){
            session.setAttribute("setTips","验证码不能为空");
        }
        else if(!Objects.equals(code,session.getAttribute("code"))){
            session.setAttribute("setTips","验证码错误");
        }
        else if ((name != null || introduce != null )&& Objects.equals(code,session.getAttribute("code"))){
            session.setAttribute("setTips","信息修改成功");
            userDao.updateInformation(user,name,introduce);
            User userObj = (User) session.getAttribute("user");
            userObj.setName(name);
            userObj.setIntroduce(introduce);
            session.setAttribute("user",userObj);
            commentDao.updateData();
            topicDao.updateData();

        }
        session.setAttribute("code",null);
        return "success";
    }



    public String setPassword(
            HttpSession session,
            HttpServletRequest req,
            String oldPassword,
            String newPassword,
            String code

    ) throws IOException {
        //        判断是否登录,防止错乱
        if(session.getAttribute("user") == null){
            return "error";
        }
        req.setCharacterEncoding("utf-8");
        User userObj = (User) session.getAttribute("user");
        String user = userObj.getUser();
        boolean flag = oldPassword.equals(userObj.getPassword());
        if(flag && Objects.equals(code,session.getAttribute("code"))){
            session.setAttribute("setTips","密码修改成功");
            userDao.updatePassword(user,newPassword);
            session.setAttribute("code",null);
        }
        else if(!Objects.equals(code,session.getAttribute("code"))){
            session.setAttribute("setTips","验证码错误");
        }
        else{
            session.setAttribute("setTips","原密码错误");
        }
        return "success";
    }

}
