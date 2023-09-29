package com.starfall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//    如果不把@GeneratedValue注释掉就无法使用userDao.save();
//    因为默认的生成策略为Long，无法匹配String类型为主键
//    解决方法：
//              1.将主键的数据类型改成Long
//              2.把主键 user 的生成策略修改为 sequence （如果您使用的是mysql数据库使用 native）
//              3.把主键 user 的生成策略修改为UUID
//                   @GeneratedValue(generator="system_uuid")
//                  @GenericGenerator(name="system_uuid",strategy="uuid")
//              4.去掉主键生成策略[最简单]
//    @GeneratedValue
    @Column(nullable = false,unique = true)
    private String user;
    @Column()
    private String password;
    @Column()
    private String date;
    @Column()
    private int level;
    @Column()
    private String name;
    @Column()
    private String introduce;
    @Column(nullable = false,unique = true)
    private String email;
    @Column()
    private String head;
    @Column
    private int promise;
    @Column
    private int exp;
    @Column
    private int signcontinuous;
}
