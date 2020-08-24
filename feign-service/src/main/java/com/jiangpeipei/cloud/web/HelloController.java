package com.jiangpeipei.cloud.web;

import com.jiangpeipei.cloud.imp.HelloFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private HelloFeignService helloFeignService;

    @GetMapping("/search/github")
    public ResponseEntity<byte[]> search(@RequestParam  String query) {

        return helloFeignService.searchRepo(query);
    }
}
