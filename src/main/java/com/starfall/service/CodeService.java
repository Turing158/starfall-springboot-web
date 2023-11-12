package com.starfall.service;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Service
public class CodeService {
    Random r = new Random();
    public BufferedImage code(
            HttpSession session,
            HttpServletResponse resp
    ) throws IOException {
        resp.setContentType("image/jpeg");
        //创建一个不带透明色的对象 图片对象 三原色
        BufferedImage bufferedImage = new BufferedImage(80,30,BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();//获取画布对象
        Color bgcolor = getBackColor();
        //画背景
        g.setColor(bgcolor);
        g.fillRect(0,0,80,30);//位置大小
        //设置前景色
        Color foreColor = getForeColor(bgcolor);
        g.setColor(foreColor);
        //设置字体
        g.setFont(new Font("黑体",Font.BOLD,26));
        //获取验证码，通过自写方法获取
        String code = getCode();
        //存入session
        session.setAttribute("code",code);
        //将验证码画入画布
        g.drawString(code,10,28);
        //随机生成大小1，1的白点
        for (int i = 0; i < 100; i++) {
            g.setColor(Color.white);
            int x = r.nextInt(80);
            int y = r.nextInt(30);
            g.fillRect(x,y,1,1);
        }
        //随机生成白线
        g.setColor(Color.white);
        int x = r.nextInt(50)+10;
        int y = r.nextInt(15)+10;
        int width = r.nextInt(10)+20;
        g.fillRect(x,y,width,2);
        return bufferedImage;
    }



    //获取验证码方法
    private String getCode(){
        char[] str = "23456789ABCDEFGHIGKMNPQSTUVWXYZabcdefghijklmnpqstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(str[r.nextInt(str.length)]);
        }
        return sb.toString();
    }
    //获取随机背景颜色
    public Color getBackColor(){
        int red = r.nextInt(256);
        int green = r.nextInt(256);
        int blue = r.nextInt(256);
        return new Color(red,green,blue);
    }
    //获取随机画笔颜色
    public Color getForeColor(Color bgColor){
        int red = 255-bgColor.getRed();
        int green = 255-bgColor.getGreen();
        int blue = 255-bgColor.getBlue();
        return new Color(red,green,blue);
    }
}
