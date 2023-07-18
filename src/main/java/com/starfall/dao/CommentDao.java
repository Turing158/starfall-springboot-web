package com.starfall.dao;

import com.starfall.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment,Long> {
    Page<Comment> findAllByTopicid(Pageable pageable,int topic);
    Page<Comment> findAllByTopicidAndUser(Pageable pageable,int topic,String user);
    int countAllByTopicid(int topic);
    int countAllByTopicidAndUser(int topic,String user);
}
