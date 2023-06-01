package com.starfall.dao;

import com.starfall.entity.Discuss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscussDao extends JpaRepository<Discuss,Long> {
    int countAllBy();
    int countAllByUser(String user);
    List<Discuss> findAll();
    List<Discuss> findAllByUser(String user);
    @Modifying
    @Query(value = "UPDATE web.discuss d INNER JOIN web.user u ON d.user = u.user SET d.head = u.head",nativeQuery = true)
    void updateHeadData();
}
