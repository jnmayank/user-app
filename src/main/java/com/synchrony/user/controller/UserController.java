package com.synchrony.user.controller;

import com.synchrony.user.dto.UserDto;
import com.synchrony.user.service.UserImageService;
import com.synchrony.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final UserImageService userImageService;

    public UserController(UserService userService, UserImageService userImageService) {
        this.userService = userService;
        this.userImageService = userImageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}/images/{imageId}")
    public void deleteImage(@RequestParam String imageId) {
        userImageService.deleteImage(imageId);
    }

    @PutMapping("{id}/images")
    public void uploadImage() {

    }
}
