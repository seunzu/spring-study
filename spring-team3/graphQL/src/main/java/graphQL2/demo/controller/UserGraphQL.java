package graphQL2.demo.controller;

import graphQL2.demo.model.User;
import graphQL2.demo.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.Optional;

@Controller
public class UserGraphQL {

    private final UserService userService;

    public UserGraphQL(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @QueryMapping
    public Optional<User> getUserById(@Argument Long id) {
        return userService.getUserById(id);
    }

    @MutationMapping
    public User createUser(@Argument String name, @Argument String email) {
        return userService.createUser(name, email);
    }

    @MutationMapping
    public User updateUser(@Argument Long id, @Argument String name, @Argument String email) {
        return userService.updateUser(id, name, email);
    }

    @MutationMapping
    public String deleteUser(@Argument Long id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }
}
