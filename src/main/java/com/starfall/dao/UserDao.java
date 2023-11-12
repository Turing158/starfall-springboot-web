package com.starfall.dao;

import com.starfall.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Transactional
@Repository
public interface UserDao extends JpaRepository<User,String> {
    User findByUser(String user);
    int countByUser(String user);
    int countByEmail(String email);
    User findByEmail(String email);
    @Modifying
    @Query(value = "update user set user.name = ?2, user.introduce = ?3 where user.user = ?1",nativeQuery = true)
    void updateInformation(String user, String name,String introduce);
    @Modifying
    @Query(value = "update user set user.password = :password where user.user = :user",nativeQuery = true)
    void updatePassword(@Param("user") String user, @Param("password") String password);
    @Modifying
    @Query(value = "update user set user.head = :head where user.user = :user",nativeQuery = true)
    void setHead(@Param("user") String user, @Param("head") String head);
}
