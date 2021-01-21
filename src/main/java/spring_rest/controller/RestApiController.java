package spring_rest.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import spring_rest.model.Role;
import spring_rest.model.User;
import spring_rest.service.RoleService;
import spring_rest.service.UserService;

import java.util.Optional;
@RequestMapping("/api")
@RestController
public class RestApiController {

    private final UserService userService;
    private final RoleService roleService;

    public RestApiController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/getRoles")
    public ResponseEntity<Iterable<Role>> getAllRoles() {
        return ResponseEntity.ok().body(roleService.getAllRoles());
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) {
        System.out.println(id);

        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        userService.deleteUserById(id);
        return "User with ID = " + id + " was deleted";
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {userService.createUser(user);

    return ResponseEntity.ok().body(user);

    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok().body(user);
    }

}
/*
запросы для postman:
метод PUT:
http://localhost:8080/api/update
{
"id" : ?,
"name": "?",
"lastName": "?",
"age": ?,
"email": "?",
"password": ?,
"roles" : "?"
}

метод POST:
http://localhost:8080/api/create
{
"name": "?",
"lastName": "?",
"age": ?,
"email": "?",
"password": ?,
"roles" : "?"
}

метод DELETE:
http://localhost:8080/api/delete/?
 */