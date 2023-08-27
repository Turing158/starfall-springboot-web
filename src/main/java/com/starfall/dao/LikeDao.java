package com.starfall.dao;

import com.starfall.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeDao extends JpaRepository<Like,Long> {

}
