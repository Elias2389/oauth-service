package com.ae.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationHandler implements AuthenticationEventPublisher {

    private final Logger log = LoggerFactory.getLogger(AuthenticationHandler.class);

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        log.info(String.format("Success authentication: %s", userDetails.getUsername()));
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException e, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        log.info(String.format("Error authentication: %s", e.getMessage()));
        log.info(String.format("Error authentication user : %s", userDetails.getUsername()));
    }
}
