package com.starfall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false)
    String content;
    @Column(nullable = false)
    String date;
    @Column(nullable = false)
    String user;
    @Column(nullable = false)
    String head;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    int topicid;
    @Column(nullable = false)
    String introduce;
}
