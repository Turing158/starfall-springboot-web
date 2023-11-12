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
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue
    Long id;
    @Column(nullable = false)
    String content;
    @Column(nullable = false)
    String date;
    @Column(nullable = false)
    String user;
    @Column()
    String head;
    @Column()
    String name;
    @Column(nullable = false)
    int topicid;
    @Column()
    String introduce;

    public Comment(String content, String date, String user, int topicid) {
        this.content = content;
        this.date = date;
        this.user = user;
        this.topicid = topicid;
    }

    public Comment(Long id, String content, String date, String user, int topicid) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.user = user;
        this.topicid = topicid;
    }
}

