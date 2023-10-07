package com.starfall.util;

public class OtherUtil {
    public String labelEC(String label){
        switch(label){
            case "serve": return "服务端";
            case "Client":return "客户端";
            case "video": return "视频";
            case "article":return "文章";
            case "plug_in":return "插件";
            case "notice": return "公告";
        }
        return "no";
    }
    public String labelCE(String label){
        switch(label){
            case "服务端": return "serve";
            case "客户端":return "Client";
            case "视频": return "video";
            case "文章":return "article";
            case "插件":return "plug_in";
            case "公告": return "notice";
        }
        return "no";
    }
    public String selectCN(String name){
        switch (name){
            case "all":
                return "综合";
            case "title":
                return "标题";
            case "content":
                return "内容";
            case "author":
                return "作者";
        }
        return "error";
    }
    public static int isLevel(int exp){
        if(exp>=0 && exp<100){
            return 0;
        }
        else if(exp>=100 && exp<240){
            return 1;
        }
        else if(exp>=240 && exp<689){
            return 2;
        }
        else if(exp>=689 && exp<1689){
            return 3;
        }
        else if(exp>=1689 && exp<3489){
            return 4;
        }
        else if(exp>=3489 && exp<5456){
            return 5;
        }
        else if(exp>=5456 && exp<8214){
            return 6;
        }
        else if(exp>=8214 && exp<12089){
            return 7;
        }
        else if(exp>=12089 && exp<18345){
            return 8;
        }
        else if(exp>=18345 && exp<26847){
            return 9;
        }
        else if(exp>=26847 && exp<38745){
            return 10;
        }
        else if(exp>=38745 && exp<49532){
            return 11;
        }
        else if(exp>=49532 && exp<70392){
            return 12;
        }
        else if(exp>=70392 && exp<999999){
            return 13;
        }
        return -1;
    }
}
