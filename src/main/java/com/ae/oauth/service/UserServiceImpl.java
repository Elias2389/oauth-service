package com.ae.oauth.service;

import com.ae.oauth.client.UserFeignClient;
import com.ae.oauth.dto.UserAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserFeignClient client;

    @Autowired
    public UserServiceImpl(final UserFeignClient client) {
        this.client = client;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth user = client.getUserByUsername(username);

        if (user == null) {
            log.error("Error user not found: " + username);
            throw new UsernameNotFoundException("Error user not found: " + username);
        }

        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .peek(simpleGrantedAuthority -> log.info("Role: " + simpleGrantedAuthority.getAuthority().toString()))
                .collect(Collectors.toList());

        log.info("User authenticated: " + username);

        return new User(user.getUsername(), user.getPassword(), user.getEnabled(),
                true, true, true, authorities);
    }

    @Override
    public UserAuth findByUsername(String username) {
        return client.getUserByUsername(username);
    }

    @Override
    public UserAuth update(UserAuth user, Long id) {
        return client.updateUser(user);
    }
}
