package com.gaoxiong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author gaoxiong
 * @ClassName GaoxiongUserDetailService
 * @Description TODO
 * @date 2019/7/10 16:52
 */
@Service
public class GaoxiongUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername ( String username ) throws UsernameNotFoundException {
        if (!username.equals("gaoxiong")) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return new User("gaoxiong", passwordEncoder.encode("123456"), AuthorityUtils.createAuthorityList("ROLE_NORMAL", "ROLE_MEDIUM"));
    }

}
