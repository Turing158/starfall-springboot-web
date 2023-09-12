package com.starfall.config;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.*;
import java.io.IOException;

@Configuration
public class WebConfiguration implements WebMvcConfigurer{
//添加资源静态文件地址
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/templates/", "classpath:/templates/");
    }
    @Bean
    public RemoteIpFilter remoteIpFilter(){
        return new RemoteIpFilter();
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new sfFilter());//先创建一个新的Filter类，方便实现方法
        registration.addUrlPatterns("/*");//
        registration.addInitParameter("name","value");//定义变量
        registration.setName("sfFilter");//设置名字
        registration.setOrder(1);//设置启动顺序
        return registration;
    }
    public class sfFilter implements Filter{

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            Filter.super.init(filterConfig);
        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            filterChain.doFilter(servletRequest,servletResponse);
        }

        @Override
        public void destroy() {
            Filter.super.destroy();
        }
    }
}
