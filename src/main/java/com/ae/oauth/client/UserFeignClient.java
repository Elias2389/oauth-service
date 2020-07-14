package com.ae.oauth.client;

import com.ae.oauth.dto.UserAuth;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.PUT;


@FeignClient(name = "user-service")
public interface UserFeignClient {

    @GetMapping("/user/username/{username}")
    public UserAuth getUserByUsername(@PathVariable("username") String username);

    @PutMapping("/user/")
    public UserAuth updateUser(@RequestBody UserAuth username);
}
