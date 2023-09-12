package com.starfall.controller;

import com.mysql.cj.util.StringUtils;
import com.starfall.Application;
import com.starfall.dao.*;
import com.starfall.entity.*;
import com.starfall.util.OtherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
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
    private GoodDao goodDao;
    private final OtherUtil otherUtil = new OtherUtil();

    //前往主题区
    @RequestMapping("/topic")
    public String topic(
            HttpSession session,
            @RequestParam(value = "label",required = false) String label,
            @RequestParam(value = "page",required = false) String page
    ){
        Topic topic = new Topic();
        topic.setSource("请选择");
        topic.setLabel("请选择");
        session.setAttribute("editTopic",topic);//初始化帖子编辑器session
        session.setAttribute("editErrorTips",null);//初始化帖子编辑器session
        int page_int = 1;
        int lastPage;
        //当page不为空指针
        if(!StringUtils.isNullOrEmpty(page)){
            page_int = Integer.parseInt(page);
        }
        String label_Chinese = null;
        session.setAttribute("labelTF",false);//初始化
        session.setAttribute("label",null);//session的label控制js的，详细看topic文件js
        //当label不为空指针
        if (!StringUtils.isNullOrEmpty(label)){
            session.setAttribute("labelTF",true);//控制"显示全部"按钮
            session.setAttribute("label","'"+label+"'");//防止session输出时，字符串没''引号
            label_Chinese = otherUtil.labelEC(label);
        }
        //分页
        Pageable pageable =PageRequest.of(page_int-1,10, Sort.by("id").ascending());
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
        Page pageObj = new Page(page_int,lastPage);
        //初始化session页数
        session.setAttribute("topicPage",pageObj);
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
        int page_int = 1;
        int html_int = 0;
        //初始化session，只看ta
        session.setAttribute("lookUser",null);
        //当page不为空时
        if (!StringUtils.isNullOrEmpty(page)){
            page_int = Integer.parseInt(page);
        }
        //当html不为空时
        if(!StringUtils.isNullOrEmpty(html)){
            html_int = Integer.parseInt(html);
        }
        //为了判断是否存在此数据
        boolean topicTF = topicDao.findById((long) html_int).isPresent();
        Topic topic;
        if (topicTF){
            int lastPage;
            //获取对应主题
            topic = topicDao.findAllById((long) html_int);
            //初始化session，主题的信息
            session.setAttribute("topic",topic);
            session.setAttribute("topicContent",topic.getContent());
            //初始化session，主题的点赞数
            session.setAttribute("topicLikeCount",goodDao.countAllByTopicidAndGood(html_int,1));
            //初始化session，此session用于判断用户是否点赞过
//            空对象是为了防止空指针
            Good goodNull = new Good();
            User userObj =(User) session.getAttribute("user");
            session.setAttribute("topicUserLike",goodNull);
//            为了防止空指针，先判断是否登录
            if(session.getAttribute("user") != null){
                //如果登录，就判断是否有点过此主题的赞或踩
                if(goodDao.existsGoodByTopicidAndUser(html_int,userObj.getUser())){
                    //如果点赞过，就把点赞信息放入session
                    session.setAttribute("topicUserLike",goodDao.findByTopicidAndUser(html_int,userObj.getUser()));
                }
            }
            //设置进入了哪个主题
            session.setAttribute("html",html_int);

            //评论区分页
            Pageable pageable =PageRequest.of(page_int-1,5, Sort.by("id").ascending());
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
            //初始化session，第几页
            Page pageObj = new Page(page_int,lastPage);
            session.setAttribute("commentPage",pageObj);
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
//        获取时间
        LocalDateTime ldt = LocalDateTime.now();
        String date = ldt.getYear()+"-"+ldt.getMonthValue()+"-"+ldt.getDayOfMonth();
//        获取点赞主题
        int html = (int) session.getAttribute("html");
//        判断是否登录,防止错乱
        if(session.getAttribute("user") == null){
            return "redirect:/topic/html?html="+html;
        }
//        获取点赞用户
        String user = ((User) session.getAttribute("user")).getUser();
//        先将点赞信息查出来
        Good goodOld = goodDao.findByTopicidAndUser(html,user);
        long id;
//        如果点赞信息存在，就通过id修改点赞状态，防止数据库数据过多
        if(goodOld != null){
//            如果点赞状态为1，即点过，直接返回页面，不执行接下来的代码
            if(goodOld.getGood() == 1){
                return "redirect:/topic/html?html="+html;
            }
            id = goodOld.getId();
        }
        else{
//            因为点赞信息不存在，数据库中存在主键，所以通过最后数据的id+1来创建新的点赞信息
            id = goodDao.findAll(Sort.by("id").descending()).get(0).getId()+1;
        }
//        创建新的点赞信息对象
        Good goodNew = new Good(id,1,user,html,date);
//        通过jpa保存
        goodDao.save(goodNew);
        return "redirect:/topic/html?html="+html;
    }
    @RequestMapping("/topic/dislike")
    public String dislike(
            HttpSession session
    ){
//        获取时间
        LocalDateTime ldt = LocalDateTime.now();
        String date = ldt.getYear()+"-"+ldt.getMonthValue()+"-"+ldt.getDayOfMonth();
//        获取点赞主题
        int html = (int) session.getAttribute("html");
        //        判断是否登录,防止错乱
        if(session.getAttribute("user") == null){
            return "redirect:/topic/html?html="+html;
        }
//        获取点赞用户
        String user = ((User) session.getAttribute("user")).getUser();
//        先将点赞信息查出来
        Good goodOld = goodDao.findByTopicidAndUser(html,user);
        long id;
//        如果点赞信息存在，就通过id修改点赞状态，防止数据库数据过多
        if(goodOld != null){
//            如果点赞状态为2，即踩过，直接返回页面，不执行接下来的代码
            if(goodOld.getGood() == 2){
                return "redirect:/topic/html?html="+html;
            }
            id = goodOld.getId();
        }
        else{
//            因为点赞信息不存在，数据库中存在主键，所以通过最后数据的id+1来创建新的点赞信息
            id = goodDao.findAll(Sort.by("id").descending()).get(0).getId()+1;
        }
//        创建新的点赞信息对象
        Good goodNew = new Good(id,2,user,html,date);
//        通过jpa保存
        goodDao.save(goodNew);
        return "redirect:/topic/html?html="+html;
    }
    @RequestMapping("/topic/cancelLike")
    public String cancelLike(
            HttpSession session
    ){
        int html = (int) session.getAttribute("html");
        //        判断是否登录,防止错乱
        if(session.getAttribute("user") == null){
            return "redirect:/topic/html?html="+html;
        }
        String user = ((User) session.getAttribute("user")).getUser();
        Good goodOld = goodDao.findByTopicidAndUser(html,user);
        if(goodOld != null){
//            这里两种方式都可以取消掉点赞，但是第二种会导致数据库中有很多无用的数据
            goodDao.deleteById(goodOld.getId());
//            goodOld.setGood(0);
//            goodDao.save(goodOld);
        }
        return "redirect:/topic/html?html="+html;
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
        //        判断是否登录,防止错乱
        if(session.getAttribute("user") == null){
            return "redirect:/topic/html?html="+html;
        }
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
            String user = ((User) session.getAttribute("user")).getUser();
            //获取调整时间
            String date = ldt.toLocalDate()+" "+ldt.getHour()+":"+ldt.getMinute()+":"+ldt.getSecond();
            //保存评论
            long id  = commentDao.findAll(Sort.by("id").descending()).get(0).getId()+1;
            commentDao.save(new Comment(id,content,date,user,html));
            //更新评论其他信息
            commentDao.updateData();
            //为了直接跳转最后一页
            lastPage = commentDao.countAllByTopicid(html);
//            更新评论数
            topicDao.updateCommentNum(html,lastPage);
//            处理最后一页
            lastPage=(lastPage+4)/5;
            //发布提示成功
            session.setAttribute("commentTips","success");
        }
        return "redirect:/topic/html?html="+html+"&page="+lastPage;
    }
    //进入编辑主题
    @RequestMapping("/topic/publish")
    public String publish(
            HttpSession session
    ){
        //        判断是否登录,防止错乱
        if(session.getAttribute("user") == null){
            return "redirect:/topic";
        }
//        return "topic/edit";
//        调试时注释掉下面，使用时不要注释
        User user = (User) session.getAttribute("user");
        if(!Objects.equals(user,null)){
            int promise = user.getPromise();
            if( user != null || promise == 10){
                return "topic/edit";
            }
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
        //        判断是否登录,防止错乱
        if(session.getAttribute("user") == null){
            return "redirect:/topic";
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
        //有空值，提示，并作修改
        if(infoNull){
            session.setAttribute("editErrorColor","border-color: darkred");
            session.setAttribute("editErrorTips","请填写完整信息，必填已标红");
        }
        //没有空值，检测验证码，验证码为空
        else if(code.isEmpty()){
            session.setAttribute("editErrorTips","验证码不能为空");
        }
        else if(session.getAttribute("code").equals(code)){
            //获取时间
            LocalDateTime ldt = LocalDateTime.now();
            String date = ldt.toLocalDate().toString();
            //获取labelHref变量[详细看数据库]
            String labelHref = otherUtil.labelCE(label);
            //获取用户名称，及发帖作者
            String user = ((User) session.getAttribute("user")).getUser();
            //获取主题id
            List<Topic> topics = topicDao.findAll(Sort.by("id").descending());
            Long id = topics.get(0).getId()+1;
            //添加主题
            topicDao.save(new Topic(id,null,label,bigTitle,user,date,0,0,labelHref,titleName,titleEnglishName,source,version,language,address,download,content,authorName));
            //更新主题一些与user相关的信息
            topicDao.updateData();
            //清除session
            session.removeAttribute("editTopic");
            session.removeAttribute("editErrorColor");
            session.removeAttribute("editErrorTips");
            return "redirect:/topic/html?html="+id;
        }
        else{
            session.setAttribute("editErrorTips","验证码错误");
        }
        Topic topicObj = new Topic(bigTitle,label,titleName,titleEnglishName,source,version,authorName,language,address,download,content);
        session.setAttribute("editTopic",topicObj);
        return "redirect:/topic/publish";
    }
    @RequestMapping("/topic/clearTips")
    @ResponseBody
    public void clearTips(
            HttpSession session
    ){
        session.setAttribute("editErrorColor",null);
        session.setAttribute("editErrorTips",null);
    }


}
