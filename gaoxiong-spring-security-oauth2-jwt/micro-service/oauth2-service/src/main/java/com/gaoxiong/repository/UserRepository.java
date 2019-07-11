package com.gaoxiong.repository;

import com.gaoxiong.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * 根据用户名查找
     * @param username
     * @return
     */
    User findByUsername ( String username );
}