package com.fundacionoasis.repository;

import com.fundacionoasis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {

    @Modifying
    @Transactional
    @Query("update User u set u.status=?1 where u.id=?2")
    void updateStatus(String status, Long id);

    @Query("select u from User u where u.email= ?1")
    Optional<User> findByEmail(String email);
}
