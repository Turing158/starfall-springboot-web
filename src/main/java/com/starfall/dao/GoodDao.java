package com.starfall.dao;

import com.starfall.entity.Good;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface GoodDao extends JpaRepository<Good,Long> {

    int countAllByTopicidAndGood(int topicid,int good);
    Good findByTopicidAndUser(int topicid,String user);
    <S extends Good> boolean existsGoodByTopicidAndUser(int topicid,String user);
}
