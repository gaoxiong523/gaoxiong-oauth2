package com.gaoxiong.gaoxiongspringsecurityjwt.service;

import com.gaoxiong.gaoxiongspringsecurityjwt.pojo.User;
import com.gaoxiong.gaoxiongspringsecurityjwt.repository.UserRepository;
import com.gaoxiong.gaoxiongspringsecurityjwt.util.JwtTokenUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author gaoxiong
 * @ClassName AuthServiceImpl
 * @Description TODO
 * @date 2019/7/10 11:20
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public ResponseEntity<User> register ( User toAdd ) {
        final String username = toAdd.getUsername();
        if (userRepository.findByUsername(username) != null) {
            return ResponseEntity.badRequest().body(new User("用户名已存在"));
        }
        String password = toAdd.getPassword();
        String encode = passwordEncoder.encode(password);
        toAdd.setPassword(encode);
        User save = userRepository.save(toAdd);
        return ResponseEntity.ok(save);
    }

    @Override
    public String login ( String username, String password ) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }
}
