package com.example.postgretest.Security;

import com.example.postgretest.Model.Hello;
import com.example.postgretest.Repository.HelloRepositoy;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

@Service
public class User implements UserDetailsService {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    HelloRepositoy helloRepositoy;

//    @Autowired JwtUtil jwtUtil;
//    @Autowired JwtFactory jwtFactory;

    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<Hello> hello = helloRepositoy.findByName(username);
        System.out.println("TOKEN : " + hello);

        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                return authorities;
            }
            //스프링 5버전 이상에서 패스워드앞에 식벽자가 있어야한다.
            //클라측에서는 서버에러를 받지만 서버에서는 경고만뜸;;;
            @Override
            public String getPassword() {
                return "{noop}test";
            }

            @Override
            public String getUsername() {
                return "user";
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }

}
