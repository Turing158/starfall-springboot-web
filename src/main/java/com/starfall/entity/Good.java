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
//对于这个文件命名的问题，不能直接命名为Like，因为Like是mysql的关键字，会报错
public class Good implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    Long id;
    @Column()
    int good;
    @Column()
    String user;
    @Column()
    int topicid;
    @Column()
    String date;

}
