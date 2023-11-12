package com.starfall.controller;

import com.starfall.Application;
import com.starfall.dao.*;
import com.starfall.entity.*;
import com.starfall.service.EditService;
import com.starfall.util.OnlineUtil;
import com.starfall.util.OtherUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@SpringBootApplication(scanBasePackageClasses = Application.class)
public class EditController {

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
    @Autowired
    private EditService editService;

//    进入编辑页面
    @RequestMapping("/administer/html")
    public String toEdit(
            HttpSession session,
            @RequestParam(required = false,defaultValue = "1") int pageU,
            @RequestParam(required = false,defaultValue = "1") int pageT,
            @RequestParam(required = false,defaultValue = "1") int pageN,
            @RequestParam(required = false,defaultValue = "1") int pageC,
            @RequestParam(required = false,defaultValue = "1") int pageG
    ){
        String status = editService.toEdit(session,pageU,pageT,pageN,pageC,pageG);
        if(status.equals("error")){
            return "redirect:/home";
        }
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
    public int getOnlineCount(){
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
            @RequestParam(required = false) String comment,
            @RequestParam(required = false) String good
    ){
        String status = editService.modify(session,user,topic,notice,comment,good);
        if(status.equals("modify")){
            return "administer/modify";
        }
        return "administer/modifyTopic";
    }

    //    添加用户
    @RequestMapping("/administer/modify/modifyUserData")
    public String modifyUser(
            HttpSession session,
            @RequestParam(required = false,defaultValue = "null") String user,
            @RequestParam(required = false,defaultValue = "") String password,
            @RequestParam(required = false,defaultValue = "") String email,
            @RequestParam(required = false,defaultValue = "0") String level,
            @RequestParam(required = false,defaultValue = "1000-01-01") String date,
            @RequestParam(required = false,defaultValue = "") String name,
            @RequestParam(required = false,defaultValue = "") String head,
            @RequestParam(required = false,defaultValue = "0") String promise,
            @RequestParam(required = false,defaultValue = "") String introduce,
            @RequestParam(value = "exp",required = false,defaultValue = "0") String exp_str,
            @RequestParam(value = "exp",required = false,defaultValue = "0") String sign_str
    ){
        editService.modifyUser(session,user,password,email,level,date,name,head,promise,introduce,exp_str,sign_str);
        return "redirect:/administer/html";
    }

    //    添加话题
    @RequestMapping("/administer/modify/modifyTopicData")
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
        int status = editService.modifyTopic(session,id,view,comment,label,bigTitle,date,user,titleName,titleEnglishName,source,version,authorName,language,address,download,content);
        System.out.println(status);
        if(status >= 0){
            return "redirect:/administer/modify?topic="+status;
        }
        return "redirect:/administer/html";
    }

    //    添加公告
    @RequestMapping("/administer/modify/modifyNoticeData")
    public String modifyNotice(
            HttpSession session,
            @RequestParam(required = false,defaultValue = "0") String id,
            @RequestParam(required = false,defaultValue = "") String content
    ){
        int status = editService.modifyNotice(session,id,content);
        if(status >= 0){
            return "redirect:/administer/modify?notice="+status;
        }
        return "redirect:/administer/html";
    }


    //    添加评论
    @RequestMapping("/administer/modify/modifyCommentData")
    public String modifyComment(
            HttpSession session,
            @RequestParam(required = false,defaultValue = "0") String id,
            @RequestParam(required = false,defaultValue = "0") String topicid,
            @RequestParam(required = false,defaultValue = "") String user,
            @RequestParam(required = false,defaultValue = "1000-01-01") String date,
            @RequestParam(required = false,defaultValue = "") String content
    ){
        int status = editService.modifyComment(session,id,topicid,user,date,content);
        if(status >= 0){
            return "redirect:/administer/modify?comment="+status;
        }
        return "redirect:/administer/html";
    }

    //    添加评论
    @RequestMapping("/administer/modify/modifyGoodData")
    public String modifyGood(
            HttpSession session,
            @RequestParam(required = false,defaultValue = "0") String id,
            @RequestParam(required = false,defaultValue = "0") String topicid,
            @RequestParam(required = false,defaultValue = "") String user,
            @RequestParam(required = false,defaultValue = "1000-01-01") String date,
            @RequestParam(required = false,defaultValue = "0") String good
    ){
        int status = editService.modifyGood(session,id,topicid,user,date,good);
        if(status >= 0){
            return "redirect:/administer/modify?good="+status;
        }
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
            @RequestParam(required = false) String comment,
            @RequestParam(required = false) String good
    ){
        editService.delete(session,user,topic,notice,comment,good);
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
        String status = editService.addDataPage(session,type);
        if(status.equals("error")){
            return "redirect:/administer/html";
        }
        if(status.equals("topic")){
            return "administer/addTopic";
        }
        return "administer/addData";
    }


//    添加用户
    @RequestMapping("/administer/addData/addUserData")
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
            @RequestParam(required = false,defaultValue = "") String introduce,
            @RequestParam(value = "exp",required = false,defaultValue = "0") String exp_str,
            @RequestParam(value = "exp",required = false,defaultValue = "0") String sign_str
    ){
        String status = editService.addUser(session,user,password,email,level,date,name,head,promise,introduce,exp_str,sign_str);
        if(status.equals("success")){
            return "redirect:/administer/html";

        }
        return "redirect:/administer/addData?type=user";
    }

//    添加话题
    @RequestMapping("/administer/addData/addTopicData")
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
        String status = editService.addTopic(session,id,view,comment,label,bigTitle,date,user,titleName,titleEnglishName,source,version,authorName,language,address,download,content);
        if(status.equals("success")){
            return "redirect:/administer/html";
        }
        return "redirect:/administer/addData?type=topic";
    }

//    添加公告
    @RequestMapping("/administer/addData/addNoticeData")
    public String addNotice(
            HttpSession session,
            @RequestParam(required = false,defaultValue = "0") String id,
            @RequestParam(required = false,defaultValue = "") String content
    ){
        String status = editService.addNotice(session,id,content);
        if(status.equals("success")){
            return "redirect:/administer/html";
        }
        return "redirect:/administer/addData?type=notice";
    }


//    添加评论
    @RequestMapping("/administer/addData/addCommentData")
    public String addComment(
            HttpSession session,
            @RequestParam(required = false,defaultValue = "0") String id,
            @RequestParam(required = false,defaultValue = "0") String topicid,
            @RequestParam(required = false,defaultValue = "") String user,
            @RequestParam(required = false,defaultValue = "1000-01-01") String date,
            @RequestParam(required = false,defaultValue = "") String content
    ){
        String status = editService.addComment(session,id,topicid,user,date,content);
        if(status.equals("success")){
            return "redirect:/administer/html";
        }
        return "redirect:/administer/addData?type=comment";
    }



    //    添加喜欢
    @RequestMapping("/administer/addData/addGoodData")
    public String addGood(
            HttpSession session,
            @RequestParam(required = false,defaultValue = "0") String id,
            @RequestParam(required = false,defaultValue = "0") String topicid,
            @RequestParam(required = false,defaultValue = "") String user,
            @RequestParam(required = false,defaultValue = "1000-01-01") String date,
            @RequestParam(required = false,defaultValue = "0") String good
    ){
        String status = editService.addGood(session,id,topicid,user,date,good);
        if(status.equals("success")){
            return "redirect:/administer/html";
        }
        return "redirect:/administer/addData?type=good";
    }

    //  有关添加功能========================================================================================


}






