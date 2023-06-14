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
public class Discuss implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue里的政策一定要GenerationType.IDENTITY，不然会报  Table 'web.hibernate_sequence' doesn't exist
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String user;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String date;
    @Column(nullable = false)
    private String head;
}
