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
public class Like implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    int like;
    @Column()
    String user;
    @Column
    long topicid;

}
