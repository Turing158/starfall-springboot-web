package com.starfall.servlet;

import com.starfall.dao.DiscussDao;
import com.starfall.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;



@MultipartConfig
@ServletSecurity
@WebServlet("/upload_head")
public class set_head extends HttpServlet {
    @Autowired
    private UserDao userDao;
    @Autowired
    private DiscussDao discussDao;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
//        UserService userService = context.getBean("userService", UserService.class);
//        DiscussService discussService = context.getBean("discussService", DiscussService.class);
        HttpSession session = req.getSession();
        session.setAttribute("display_me","none");
        session.setAttribute("display_i","none");
        session.setAttribute("display_p","none");
        session.setAttribute("display_h","block");
//        改编码，以免乱码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
//        直接获取input的name的值
        Part part = req.getPart("head_img");
//        这个是保存头像的，现在保存只能临时的，这个填存服务器头像的存储位置，可永久保存
//        String savePath = "";
        String user = (String) session.getAttribute("user");
//        获取文件后缀
        String fileType = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));
//        设置存储位置
        String path = getServletContext().getRealPath("/WEB-INF/web/head_img/");
//        System.out.println(req.getRealPath(req.getServletPath()));
//        写入保存的路径
        part.write(path+user+fileType);
//        永久保存头像，避免服务器崩溃导致丢失
//        part.write(savePath+user+fileType);
        userDao.setHead(user,user+fileType);
//        更新数据库里头像的名字
        discussDao.updateHeadData();
        session.setAttribute("head",user+fileType);
        resp.sendRedirect("/set");
    }
}
