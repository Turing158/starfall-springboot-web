package com.starfall.controller;

import com.starfall.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


//验证码控制器
@Controller
@SpringBootApplication
public class CodeController {
    @Autowired
    private CodeService codeService;
    @RequestMapping("/jpegCode")
    public void code(
            HttpSession session,
            HttpServletResponse resp
    ) throws IOException {
        resp.setContentType("image/jpeg");
        //获取servlet输出流
        ServletOutputStream sos = resp.getOutputStream();
        //将图片以IO流方式输出
        ImageIO.write(codeService.code(session,resp),"jpeg",sos);
    }
}
