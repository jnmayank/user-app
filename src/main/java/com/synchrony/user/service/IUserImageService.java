package com.synchrony.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.synchrony.user.dto.ImageResponse;
import com.synchrony.user.dto.ImageRequest;
import com.synchrony.user.dto.UserImageRequest;
import org.springframework.http.ResponseEntity;

public interface IUserImageService {
    void deleteImage(String imageId, String code);

    ResponseEntity<ImageResponse> fetchImage(String imageHash, String code);

    ResponseEntity<ImageResponse> uploadImage(ImageRequest imageRequest, String code) throws JsonProcessingException;

    void associateImagesWithUser(String userId, UserImageRequest imageRequest);


}
