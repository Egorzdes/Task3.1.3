package spring_rest.service;

import org.springframework.stereotype.Component;
import spring_rest.model.Role;
import spring_rest.model.User;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class SetData {
    private UserService userService;
    private RoleService roleService;

    public SetData(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void Init() {
        Set<Role> allRoles = new HashSet<>();
        allRoles.add(new Role("ADMIN"));
        allRoles.add(new Role("USER"));
        roleService.createRoles(allRoles);
        User admin = new User("admin", "admin", 33, "admin@mail.ru", "admin");
        admin.setRoles("ADMIN, USER");
        userService.createUser(admin);
        User user = new User("user", "user", 10, "user@mail.ru", "user");
        user.setRoles("USER");
        userService.createUser(user);
        User user2 = new User("user2", "user2", 20, "user2@mail.ru", "user2");
        user2.setRoles("USER");
        userService.createUser(user2);

    }
}
