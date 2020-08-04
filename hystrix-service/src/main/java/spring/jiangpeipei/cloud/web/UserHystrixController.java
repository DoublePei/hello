package spring.jiangpeipei.cloud.web;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import spring.jiangpeipei.cloud.entity.CommonResult;
import spring.jiangpeipei.cloud.service.UserService;

@RestController ("/user")
public class UserHystrixController {


    @Autowired
    private UserService userService;

    @GetMapping ("/testFallback/{id}")
    public CommonResult testFallback(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping ("/testCommand/{id}")
    public CommonResult testCommand(@PathVariable Long id) {
        return userService.getUserCommand(id);
    }

    @GetMapping ("/testException/{id}")
    public CommonResult testException(@PathVariable Long id) {
        return userService.getUserException(id);
    }

    /**
     * 请求缓存
     * @param id
     * @return
     */
    @GetMapping ("/testCache/{id}")
    public CommonResult testCache(@PathVariable Long id) {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            userService.getUserCache(id);
            userService.getUserCache(id);
            userService.getUserCache(id);
        } finally {
            context.close();
        }
        return new CommonResult("操作成功", 200);
    }

}
