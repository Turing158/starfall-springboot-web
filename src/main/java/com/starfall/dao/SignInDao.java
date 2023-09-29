package com.starfall.dao;

import com.starfall.entity.SignIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface SignInDao extends JpaRepository<SignIn, Long> {
    List<SignIn> findAllByUserOrderByDateDesc(String user);
    boolean existsAllByUserAndDateLike(String user, String date);
}
