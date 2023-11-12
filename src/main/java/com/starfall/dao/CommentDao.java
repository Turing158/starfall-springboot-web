package com.starfall.dao;

import com.starfall.entity.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface CommentDao extends JpaRepository<Comment,Long> {
    Page<Comment> findAllByTopicid(Pageable pageable,int topic);
    Page<Comment> findAllByTopicidAndUser(Pageable pageable,int topic,String user);
    int countAllByTopicid(int topic);
    int countAllByTopicidAndUser(int topic,String user);
    @Modifying
    @Query(value = "UPDATE web.comment c INNER JOIN web.user u ON c.user = u.user SET c.head = u.head, c.name = u.name, c.introduce = u.introduce",nativeQuery = true)
    void updateData();
}
