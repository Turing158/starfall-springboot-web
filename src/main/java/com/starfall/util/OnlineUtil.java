package com.starfall.util;

import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
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
    }

    @Override
    public synchronized void sessionDestroyed(HttpSessionEvent httpSessionEvent) {//监听session的撤销
        HttpSession session = httpSessionEvent.getSession();
        list.remove(session.getId());
        System.out.println("-");
    }
}
