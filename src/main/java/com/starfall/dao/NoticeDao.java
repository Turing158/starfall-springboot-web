package com.starfall.dao;

import com.starfall.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface NoticeDao extends JpaRepository<Notice,Long> {
    List<Notice> findAll();
}
