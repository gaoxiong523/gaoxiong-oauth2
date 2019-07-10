package com.gaoxiong.gaoxiongspringsecurityjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.gaoxiong.gaoxiongspringsecurityjwt.pojo.User;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * 根据用户名查找
     * @param username
     * @return
     */
    User findByUsername(String username);
}