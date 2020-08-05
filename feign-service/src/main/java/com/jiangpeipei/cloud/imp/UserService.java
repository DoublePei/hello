package com.jiangpeipei.cloud.imp;

import com.jiangpeipei.cloud.entity.CommonResult;
import com.jiangpeipei.cloud.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "user-service")
public interface UserService {

    @PostMapping ("/user/create")
    CommonResult create(@RequestBody User user);

    @GetMapping ("/user/{id}")
    CommonResult<User> getUser(@PathVariable Long id);

    @GetMapping("/user/getByUsername")
    CommonResult<User> getByUsername(@RequestParam String username);

    @PostMapping("/user/update")
    CommonResult update(@RequestBody User user);

    @PostMapping("/user/delete/{id}")
    CommonResult delete(@PathVariable Long id);

}