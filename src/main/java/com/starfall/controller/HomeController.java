package com.starfall.controller;

import com.starfall.Application;
import com.starfall.dao.DiscussDao;
import com.starfall.dao.NoticeDao;
import com.starfall.dao.UserDao;
import com.starfall.entity.Discuss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


//主页控制器
@Controller
@SpringBootApplication(scanBasePackageClasses = Application.class)
public class HomeController{

    @Autowired
    private DiscussDao discussDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private NoticeDao noticeDao;


//进入主页与返回主页的控制
    @RequestMapping(value = {"/home","/"})
    public String home(
            HttpSession session,
            @RequestParam(value = "only_user",required = false) String only_user,
            @RequestParam(value = "page",required = false) String page_str
    ) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
//        DiscussService discussService = context.getBean("discussService", DiscussService.class);
        session.setAttribute("noticeLength",noticeDao.count());
//        System.out.println(noticeDao.count());
        session.setAttribute("notices",noticeDao.findAll());
        //判断有没有传入“只看”
        if (Objects.equals(only_user,"null")){
            only_user = "";
        }
        //将“只看”传入session
        session.setAttribute("only_user",only_user);
        //默认第一页，page页数
        Integer page = 1;
        session.setAttribute("page",1);
        //传入翻看页数
        if (!StringUtils.isEmpty(page_str)){
            page = Integer.valueOf(page_str);
        }
        //总页数
        int last_page = (discussDao.countAllBy()+4)/5;
//        if(Objects.equals(only_user,"")){
//            last_page = 1;
//        }
        //用于判断评论数
        int count = page*5;
        //新建输出数组
        List<Discuss> discussList = new ArrayList<>();

        //没有传入“只看”
        if(Objects.equals(only_user,"") || Objects.equals(only_user,null)){
            List<Discuss> discuss_data = discussDao.findAll();//获取评论
//            System.out.println(discuss_data.toString());
            //用于判断最后一页不满5个，防止数组越界
            if((page)*5 > discussDao.countAllBy()){
                count = discussDao.countAllBy();
            }
//            System.out.println(count);
            //获取五个评论
            for (int i = (page-1)*5; i < count; i++) {
                discussList.add(discuss_data.get(i));
            }
        }
        else {
            //同上，这是传入“只看”
            last_page = (discussDao.countAllByUser(only_user)+4)/5;
            List<Discuss> discuss_data = discussDao.findAllByUser(only_user);
            if((page)*5 > discussDao.countAllByUser(only_user)){
                count = discussDao.countAllByUser(only_user);
            }
            for (int i = (page-1)*5; i < count; i++) {
                discussList.add(discuss_data.get(i));
            }
        }

        //设置session
        session.setAttribute("comment",discussList);
        session.setAttribute("last_page",last_page);
        session.setAttribute("page",page);
        session.setAttribute("page_center",page+"/"+last_page);
        //防止页面的page出问题
        if(page > last_page){
            session.setAttribute("page",page-1);
        }
        else if(page <= 0){
            session.setAttribute("page",1);
        }
        return "index";
    }


    //添加评论
    @RequestMapping("/add_comment")
    public String addComment(
            HttpSession session,
            HttpServletRequest req,
            @RequestParam(value = "content",required = false)String content,
            @RequestParam(value = "comment_VerifyCode",required = false)String code
    ) throws UnsupportedEncodingException {
//        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
//        UserService userService = context.getBean("userService", UserService.class);
//        DiscussService discussService = context.getBean("discussService", DiscussService.class);
        //获取时间
        LocalDateTime ldt = LocalDateTime.now();
        //将页面调至comment的session
        session.setAttribute("comment","block");
        session.setAttribute("home","none");
        //设置传入编码为utf-8
        req.setCharacterEncoding("utf-8");
        //设置添加评论时间
        String date = LocalDate.now()+" "+ldt.getHour()+":"+ldt.getMinute()+":"+ldt.getSecond();
        //设置添加评论用户
        String user = (String) session.getAttribute("user");
        //设置添加评论用户名称
        String name = userDao.findByUser(user).getName();
        //方便保存输入的评论，以免输错验证码刷掉
        session.setAttribute("comment_input",content);

        //全部符合要求添加评论
        if(Objects.equals(code,session.getAttribute("code"))){
//            discussService.addComment(user,content,date,name);
            discussDao.save(new Discuss((long) (discussDao.countAllBy()+1),user,name,content,date,userDao.findByUser(user).getHead()));
//            discussService.updateHead();
            discussDao.updateHeadData();
            //回馈
            session.setAttribute("comment_tips","发话成功");
            session.setAttribute("comment_input",null);
            session.setAttribute("code",null);
        }
        else{
            session.setAttribute("comment_tips","验证码错误");
        }
        return "redirect:/home";
    }

    //退出，将session清空
    @RequestMapping("/exit")
    public String exit(
            HttpSession session
    ){
        session.invalidate();
        return "redirect:/home";
    }
}

