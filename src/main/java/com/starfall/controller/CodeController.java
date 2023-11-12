package com.starfall.controller;

import com.starfall.service.CodeService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.io.IOException;


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
