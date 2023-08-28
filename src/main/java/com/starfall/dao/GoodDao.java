package com.starfall.dao;

import com.starfall.entity.Good;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface GoodDao extends JpaRepository<Good,Long> {
    @Override
    Page<Good> findAll(Pageable pageable);
}
