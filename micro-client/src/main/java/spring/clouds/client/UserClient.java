package spring.clouds.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.clouds.bean.User;

@FeignClient("users")
public interface UserClient {

    //@Override
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = "application/json")
    User findOne(@PathVariable("id") String id);
}
