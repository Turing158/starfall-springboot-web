package com.starfall.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//            不注释掉不能自主设置id，管理面板需要用到
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column()
    String icon;
    @Column(nullable = false)
    String label;
    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    String user;
    @Column(nullable = false)
    String date;
    @Column()
    int comment;
    @Column()
    int view;
    @Column(nullable = false)
    String labelHref;
    @Column(nullable = false)
    String titlename;
    @Column(nullable = false)
    String titleenglishname;
    @Column(nullable = false)
    String source;
    @Column(nullable = false)
    String version;
    @Column(nullable = false)
    String language;
    @Column(nullable = false)
    String address;
    @Column(nullable = false)
    String download;
    @Column(nullable = false)
    String content;
    @Column(nullable = false)
    String authorname;
    @Column()
    String username;
    @Column()
    String userhead;
    @Column()
    String userinformation;
    public Topic(Long id, String icon, String label, String title, String user, String date, int comment, int view, String labelHref, String titlename, String titleenglishname, String source, String version, String language, String address, String download, String content, String authorname) {
        this.id = id;
        this.icon = icon;
        this.label = label;
        this.title = title;
        this.user = user;
        this.date = date;
        this.comment = comment;
        this.view = view;
        this.labelHref = labelHref;
        this.titlename = titlename;
        this.titleenglishname = titleenglishname;
        this.source = source;
        this.version = version;
        this.language = language;
        this.address = address;
        this.download = download;
        this.content = content;
        this.authorname = authorname;
    }

    public Topic( String title, String label, String titlename, String titleenglishname, String source, String version, String language , String authorname, String address, String download, String content) {
        this.title = title;
        this.label = label;
        this.titlename = titlename;
        this.titleenglishname = titleenglishname;
        this.source = source;
        this.version = version;
        this.language = language;
        this.authorname = authorname;
        this.address = address;
        this.download = download;
        this.content = content;

    }
}
