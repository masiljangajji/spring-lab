package com.masiljangajji.scheduler.presentation;

import com.masiljangajji.scheduler.application.UserService;
import com.masiljangajji.scheduler.domain.User;
import com.masiljangajji.scheduler.domain.UserStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserRegisterRequest request) {
        User user = User.of(request.name(), UserStatus.from(request.status()));
        userService.insertUser(user);

        URI location = URI.create("/users/" + user.getId());
        return ResponseEntity.created(location).body(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUsers(@PathVariable UUID id) {
        User user = userService.findUserById(id);
        return ResponseEntity.ok()
                .body(user);
    }

}
