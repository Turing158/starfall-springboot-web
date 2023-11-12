package com.starfall.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



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
