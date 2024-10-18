package com.example.demo.user.domain;

import com.example.demo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;
    private final UserModelAssembler assembler;

    @Autowired
    public UserController(UserRepository userRepository , UserModelAssembler assembler) {
        this.userRepository = userRepository;
        this.assembler = assembler;
    }

    @PostMapping
    public ResponseEntity<EntityModel<User>> createMember(@RequestBody User user) {

        User savedUser = userRepository.save(user);

        EntityModel<User> userModel = assembler.toModel(savedUser);

        return ResponseEntity
                .created(linkTo(methodOn(UserController.class).getUser(savedUser.getId())).toUri())
                .body(userModel);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<User>>> getAllUsers() {
        List<EntityModel<User>> users = userRepository.findAll().stream()
                .map(user -> assembler.toModel(user))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(users,
                linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<User>> getUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow();
        EntityModel<User> userModel = assembler.toModel(user);
        return ResponseEntity.ok(userModel);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EntityModel<User>> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EntityModel<User>> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());

        User savedUser = userRepository.save(user);

        return ResponseEntity
                .ok(assembler.toModel(savedUser));
    }
}
