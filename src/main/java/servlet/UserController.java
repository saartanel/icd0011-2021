package servlet;

import config.ControllerConfig;
import dao.UserDao;
import mapper.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {


    @GetMapping("users/{username}")
    @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
    public User getUserByName(@PathVariable String username) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ControllerConfig.class);
        UserDao userDao = context.getBean(UserDao.class);

        return userDao.getUserByUserName(username);
    }

    @GetMapping("users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {

        ApplicationContext context = new AnnotationConfigApplicationContext(ControllerConfig.class);
        UserDao userDao = context.getBean(UserDao.class);

        return userDao.getALlUsers();
    }
}
