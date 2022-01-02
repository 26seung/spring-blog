package com.one.blog.repository;

import com.one.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User>findByUsername(String username);
    // 네이티브 쿼리 방식
//    @Query(value="SELECT * FROM user WHERE username=?1 AND password=?2",nativeQuery = true)
//    User login(String username, String password);
    // JPA Naming 쿼리
    User findByUsernameAndPassword(String username, String password);
}
