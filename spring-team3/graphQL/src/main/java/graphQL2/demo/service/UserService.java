package graphQL2.demo.service;

import graphQL2.demo.model.User;
import graphQL2.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(String name, String email, String hobby, String favoriteColor) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setHobby(hobby);
        user.setFavoriteColor(favoriteColor);
        return userRepository.save(user);
    }

    public User updateUser(Long id, String name, String email, String hobby, String favoriteColor) {
        User user = userRepository.findById(id).orElseThrow();
        if (name != null) user.setName(name);
        if (email != null) user.setEmail(email);
        if (hobby != null) user.setHobby(hobby);
        if (favoriteColor != null) user.setFavoriteColor(favoriteColor);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
