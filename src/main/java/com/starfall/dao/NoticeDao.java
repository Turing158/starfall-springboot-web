package com.starfall.dao;

import com.starfall.entity.Notice;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface NoticeDao extends JpaRepository<Notice,Long> {
    List<Notice> findAll();
}
