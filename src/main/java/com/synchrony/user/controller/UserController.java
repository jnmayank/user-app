package com.synchrony.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.synchrony.user.dto.ImageResponse;
import com.synchrony.user.dto.ImageRequest;
import com.synchrony.user.dto.User;
import com.synchrony.user.dto.UserImageRequest;
import com.synchrony.user.entity.AppUser;
import com.synchrony.user.service.UserImageService;
import com.synchrony.user.service.UserService;
import jakarta.websocket.server.PathParam;
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
    public ResponseEntity createUser(@RequestBody User userDto) {
        userService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}/images/{imageId}")
    public void deleteImage(@PathVariable("imageId") String imageId, @RequestParam String code) {
        userImageService.deleteImage(imageId, code);
    }

    @GetMapping("/{id}/images/{imageHash}")
    public ResponseEntity<ImageResponse> fetchImage(@PathVariable("imageHash") String imageHash,
                                                    @RequestParam String code) {
        return userImageService.fetchImage(imageHash, code);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<AppUser> fetchImage(@PathVariable("userName") String userName) {
        AppUser appUser  = userService.fetchUser(userName);
        return ResponseEntity.ok(appUser);
    }

    @PostMapping("{id}/images")
    public void uploadImage(@RequestBody ImageRequest imageRequest, @RequestParam String code)
            throws JsonProcessingException {
        ResponseEntity.status(HttpStatus.CREATED).body(userImageService.uploadImage(imageRequest, code));

    }

    @PutMapping("{id}/images")
    public ResponseEntity associateImagesWithUser(@PathVariable("id") String userId, @RequestBody UserImageRequest imageRequest)
            throws JsonProcessingException {
        userImageService.associateImagesWithUser(userId, imageRequest);
        return ResponseEntity.ok().build();
    }
}
