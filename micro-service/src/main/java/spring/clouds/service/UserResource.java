package spring.clouds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spring.clouds.Bean.User;
import spring.clouds.Bean.UserId;
import spring.clouds.Bean.UserRepresentation;
import spring.clouds.exception.NotFoundException;
import spring.clouds.repo.UserRepository;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    public UserRepresentation findOne(@PathVariable("id") String id) {
        User user = userRepository.findByUserId(new UserId(id));
        if (user == null || user.getDeleted()) {
            throw new NotFoundException("指定ID的用户不存在或者已被删除。");
        }
        return new UserRepresentation(user);
    }
}
