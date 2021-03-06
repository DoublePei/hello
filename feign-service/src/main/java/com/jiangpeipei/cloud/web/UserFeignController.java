package com.jiangpeipei.cloud.web;

import com.jiangpeipei.cloud.entity.CommonResult;
import com.jiangpeipei.cloud.entity.User;
import com.jiangpeipei.cloud.imp.HelloFeignService;
import com.jiangpeipei.cloud.imp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserFeignController {

    @Autowired
    private UserService userService;

    @Autowired
    private HelloFeignService helloFeignService;

    @GetMapping ("/{id}")
    public CommonResult getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/getByUsername")
    public CommonResult getByUsername(@RequestParam String username) {
        return userService.getByUsername(username);
    }

    @PostMapping ("/create")
    public CommonResult create(@RequestBody User user) {
        return userService.create(user);
    }

    @PostMapping("/update")
    public CommonResult update(@RequestBody User user) {
        return userService.update(user);
    }

    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    @GetMapping("/search/github")
    public ResponseEntity<byte[]> search(@RequestParam  String query) {

        return helloFeignService.searchRepo(query);
    }
}
