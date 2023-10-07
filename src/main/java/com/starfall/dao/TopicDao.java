package com.starfall.dao;

import com.starfall.controller.TopicController;
import com.starfall.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TopicDao extends JpaRepository<Topic,Long> {
    Page<Topic> findAll(Pageable pageable);
    Page<Topic> findAllByUser(Pageable pageable,String user);
    Page<Topic> findAllByLabel(String label,Pageable pageable);
    int countAllByUser(String user);
    int countAllBy();
    int countAllByLabel(String label);
    //    Topic findById(int num);
    @Modifying
    @Query(value = "UPDATE web.topic SET comment = ?2 WHERE id = ?1",nativeQuery = true)
    void updateCommentNum(int id,int num);
    Topic findAllById(Long id);
    @Modifying
    @Query(value = "UPDATE web.topic t INNER JOIN web.user u ON t.user = u.user SET t.userhead = u.head, t.username = u.name, t.userinformation = u.introduce",nativeQuery = true)
    void updateData();

    Page<Topic> findAllByTitleLikeOrContentLikeOrUsername(Pageable page,String title,String content,String username);

    @Query(value = "select * from topic where title like %?1% or content like %?1% limit ?2,10",nativeQuery = true)
    List<Topic> searchTopic(String search, int page);

    @Query(value = "select count(*) from topic where title like %?1% or content like %?1%",nativeQuery = true)
    int countSearch(String search);
}
