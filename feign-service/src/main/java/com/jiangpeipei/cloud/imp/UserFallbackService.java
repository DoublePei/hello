package com.jiangpeipei.cloud.imp;

import com.jiangpeipei.cloud.entity.CommonResult;
import com.jiangpeipei.cloud.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * 指定降级类，而不是出错 导致后续服务不可用
 */
@Component
public class UserFallbackService implements UserService ,HelloFeignService{

    @Override
    public CommonResult create(User user) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult(defaultUser);
    }

    @Override
    public CommonResult<User> getUser(Long id) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult(defaultUser);
    }

    @Override
    public CommonResult<User> getByUsername(String username) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult(defaultUser);
    }

    @Override
    public CommonResult update(User user) {
        return new CommonResult("调用失败，服务被降级", 500);
    }

    @Override
    public CommonResult delete(Long id) {
        return new CommonResult("调用失败，服务被降级", 500);
    }

    @Override
    public ResponseEntity<byte[]> searchRepo(String q) {
        return null;
    }
}
