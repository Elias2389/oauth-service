package com.ae.oauth.security;

import com.ae.oauth.security.event.AuthenticationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService service;
    private final AuthenticationHandler authenticationHandler;

    @Autowired
    public SpringSecurityConfig(@Qualifier("userServiceImpl") UserDetailsService service,
                                AuthenticationHandler authenticationHandler) {
        this.service = service;
        this.authenticationHandler = authenticationHandler;
    }

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.service)
            .passwordEncoder(getPasswordEncoder())
            .and()
            .authenticationEventPublisher(authenticationHandler);
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
