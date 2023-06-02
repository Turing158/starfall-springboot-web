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
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(nullable = false,unique = true)
    private String user;
    @Column()
    private String password;
    @Column()
    private String date;
    private int level;
    @Column()
    private String name;
    @Column()
    private String introduce;
    @Column(nullable = false,unique = true)
    private String email;
    @Column()
    private String head;
}
