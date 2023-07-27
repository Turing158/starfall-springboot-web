package com.starfall.dao;

import com.starfall.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface TopicDao extends JpaRepository<Topic,Long> {
    Page<Topic> findAll(Pageable pageable);
    Page<Topic> findAllByLabel(String label,Pageable pageable);
    int countAllBy();
    int countAllByLabel(String label);
//    Topic findById(int num);
    @Override
    Optional<Topic> findById(Long aLong);
}
