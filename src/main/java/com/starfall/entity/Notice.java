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
public class Notice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue
    Long id;
    @Column(nullable = false)
    String content;
}
