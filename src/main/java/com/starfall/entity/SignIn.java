package com.starfall.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
