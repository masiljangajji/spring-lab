package com.masiljangajji.scheduler.presentation;

import com.masiljangajji.scheduler.application.UserService;
import com.masiljangajji.scheduler.domain.User;
import com.masiljangajji.scheduler.presentation.dto.UserGetResponse;
import com.masiljangajji.scheduler.presentation.dto.UserCreateRequest;
import com.masiljangajji.scheduler.presentation.dto.UserCreateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserCreateResponse> registerUser(@Valid @RequestBody UserCreateRequest request) {

        User user = userService.insertUser(request.name());

        URI location = URI.create("/users/" + user.getId());
        return ResponseEntity.created(location).body(UserCreateResponse.from(user));
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
