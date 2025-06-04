package com.masiljangajji.scheduler.presentation;

import com.masiljangajji.scheduler.application.UserService;
import com.masiljangajji.scheduler.domain.User;
import com.masiljangajji.scheduler.presentation.dto.UserGetResponse;
import com.masiljangajji.scheduler.presentation.dto.UserRegisterRequest;
import com.masiljangajji.scheduler.presentation.dto.UserRegisterResponse;
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
    public ResponseEntity<UserRegisterResponse> registerUser(@Valid @RequestBody UserRegisterRequest request) {

        User user = userService.insertUser(request.name(), request.status());

        URI location = URI.create("/users/" + user.getId());
        return ResponseEntity.created(location).body(UserRegisterResponse.from(user));
    }

    @GetMapping
    public ResponseEntity<List<UserGetResponse>> getUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(UserGetResponse.from(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetResponse> getUser(@PathVariable UUID id) {
        User user = userService.findUserById(id);
        return ResponseEntity.ok()
                .body(UserGetResponse.from(user));
    }

}
