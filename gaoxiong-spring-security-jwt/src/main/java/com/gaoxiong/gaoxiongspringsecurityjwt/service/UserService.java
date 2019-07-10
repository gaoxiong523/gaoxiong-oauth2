package com.gaoxiong.gaoxiongspringsecurityjwt.service;

import com.gaoxiong.gaoxiongspringsecurityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author com.gaoxiong
 * @ClassName UserService
 * @Description TODO
 * @date 2019/7/10 11:58
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername ( String username ) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
