package com.starfall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic implements Serializable {
    @Id
    @GeneratedValue
    Long id;
    @Column()
    String icon;
    @Column(nullable = false)
    String label;
    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    String author;
    @Column(nullable = false)
    String date;
    @Column()
    int comment;
    @Column()
    int view;
    @Column(nullable = false)
    String href;
    @Column(nullable = false)
    String labelHref;
    @Column(nullable = false)
    String titlename;
    @Column(nullable = false)
    String titleenglishname;
    @Column(nullable = false)
    String version;
    @Column(nullable = false)
    String language;
    @Column(nullable = false)
    String address;
    @Column(nullable = false)
    String download;
    @Column(nullable = false)
    String source;
    @Column(nullable = false)
    String content;
}
