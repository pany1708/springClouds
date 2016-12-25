package spring.clouds.repo;

import org.springframework.stereotype.Repository;
import spring.clouds.Bean.User;
import spring.clouds.Bean.UserId;

/**
 * Created by lennylv on 2016-12-22.
 */
@Repository
public interface UserRepository {
    User findByUserId(UserId userId);
}
