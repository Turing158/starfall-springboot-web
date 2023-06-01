package com.starfall.dao;

import com.starfall.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends JpaRepository<User,Long> {
    User findByUser(String user);
    int countByUser(String user);
    int countByEmail(String email);
    @Modifying
    @Query(value = "update user u set u.name = :name, u.introduce = :introduce where u.user = :user",nativeQuery = true)
    void updateInformation(@Param("user") String user, @Param("name") String name,@Param("introduce")String introduce);
    @Modifying
    @Query(value = "update user u set u.password = :password where u.user = :user",nativeQuery = true)
    void updatePassword(@Param("user") String user, @Param("password") String password);

}
