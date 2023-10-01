package com.starfall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "sign_in")
public class SignIn implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    int id;
    @Column
    String user;
    @Column
    String date;
    @Column
    String comment;
    @Column
    String name;
}
