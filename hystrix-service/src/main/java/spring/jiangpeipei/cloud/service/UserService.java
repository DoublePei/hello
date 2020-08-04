package spring.jiangpeipei.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import spring.jiangpeipei.cloud.entity.CommonResult;
import spring.jiangpeipei.cloud.entity.User;

@Service
@Slf4j
public class UserService {
    @Value ("${service-url.user-service}")
    private String userServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 基础降级
     *
     * @param id
     * @return
     */
    @HystrixCommand (fallbackMethod = "getDefaultUser")
    public CommonResult getUser(Long id) {
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", CommonResult.class, id);
    }

    public CommonResult getDefaultUser(@PathVariable Long id) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult<>(defaultUser);
    }

    /**
     * 降级详情信息
     *
     * @param id
     * @return
     */
    @HystrixCommand (fallbackMethod = "getDefaultUser", commandKey = "getUserCommand", groupKey = "getUserGroup", threadPoolKey = "getUserThreadPool")
    public CommonResult getUserCommand(@PathVariable Long id) {
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", CommonResult.class, id);
    }

    /**
     * 过滤掉部分异常
     *
     * @param id
     * @return
     */
    @HystrixCommand (fallbackMethod = "getDefaultUser2", ignoreExceptions = {NullPointerException.class})
    public CommonResult getUserException(Long id) {
        if (id == 1) {
            throw new ArrayIndexOutOfBoundsException("ArrayIndexOutOfBoundsException");
        }
        if (id == 2) {
            throw new NullPointerException("NullPointerException");
        }
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", CommonResult.class, id);
    }

    public CommonResult getDefaultUser2(@PathVariable Long id) {
        User defaultUser = new User(-2L, "defaultUser", "123456");
        return new CommonResult<>(defaultUser);
    }

    @CacheResult (cacheKeyMethod = "getCacheKey")
    @HystrixCommand (fallbackMethod = "getDefaultUser", commandKey = "getUserCache")
    public CommonResult getUserCache(Long id) {
        log.info("get user cache id : {}", id);
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", CommonResult.class, id);
    }

    public String getCacheKey(Long id) {
        return String.valueOf(id + "______");
    }


}
