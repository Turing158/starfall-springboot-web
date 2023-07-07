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
    @Column(nullable = false)
    String icon;
    @Column(nullable = false)
    String label;
    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    String author;
    @Column(nullable = false)
    String date;
    @Column(nullable = false)
    int comment;
    @Column(nullable = false)
    int view;
    @Column(nullable = false)
    String head;
    @Column(nullable = false)
    String href;
    @Column(nullable = false)
    String labelHref;
}
