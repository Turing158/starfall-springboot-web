package com.starfall.dao;

import com.starfall.entity.SignIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface SignInDao extends JpaRepository<SignIn, Long> {
    List<SignIn> findAllByUserOrderByDateDesc(String user);
    boolean existsAllByUserAndDateLike(String user, String date);
    @Modifying
    @Query(value = "UPDATE web.sign_in s INNER JOIN web.user u ON s.user = u.user SET s.name = u.name",nativeQuery = true)
    void updateData();
}
