package com.jiangpeipei.cloud.imp;

import com.jiangpeipei.cloud.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "github-client",url = "https://api.github.com",configuration = FeignConfig.class,fallback = UserFallbackService.class)
public interface HelloFeignService {

    @GetMapping("/search/repositories")
    ResponseEntity<byte[]> searchRepo(@RequestParam("q") String q);
}
