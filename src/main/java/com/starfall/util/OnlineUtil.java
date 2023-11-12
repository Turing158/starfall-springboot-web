package com.starfall.util;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@WebListener
public class OnlineUtil implements HttpSessionListener {
    public static List<String> list = new ArrayList<String>();

    //监听session的创建,synchronized 防并发bug
    @Override
    public synchronized void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        list.add(session.getId());
//        System.out.println("+");
    }

    @Override
    public synchronized void sessionDestroyed(HttpSessionEvent httpSessionEvent) {//监听session的撤销
        HttpSession session = httpSessionEvent.getSession();
        list.remove(session.getId());
//        System.out.println("-");
    }
}
