package com.jiangpeipei.cloud.web;

import com.jiangpeipei.cloud.entity.CommonResult;
import com.jiangpeipei.cloud.entity.User;
import com.jiangpeipei.cloud.imp.HelloFeignService;
import com.jiangpeipei.cloud.imp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private HelloFeignService helloFeignService;

    @GetMapping("/search/github")
    public String search(@RequestParam  String query) {

        return helloFeignService.searchRepo(query);
    }
}
