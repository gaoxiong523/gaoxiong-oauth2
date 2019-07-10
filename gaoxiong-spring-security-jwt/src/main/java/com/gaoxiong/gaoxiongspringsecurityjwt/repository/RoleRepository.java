package com.gaoxiong.gaoxiongspringsecurityjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.gaoxiong.gaoxiongspringsecurityjwt.pojo.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role> {

}