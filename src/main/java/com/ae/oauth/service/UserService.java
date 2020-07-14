package com.ae.oauth.service;

import com.ae.oauth.dto.UserAuth;

public interface UserService {
    public UserAuth findByUsername(String username);

    public UserAuth update(UserAuth user, Long id);
}
