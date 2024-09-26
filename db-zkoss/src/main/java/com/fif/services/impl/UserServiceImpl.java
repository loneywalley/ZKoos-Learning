package com.fif.services.impl;


import com.fif.entity.User;
import com.fif.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service("userService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        try {
            User user = userRepository.get(username);
            System.out.println(user);
            Set<GrantedAuthority> authoritySet = user.getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
            return new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(),authoritySet);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw new RuntimeException("Invalid Login");
        }
    }
}
