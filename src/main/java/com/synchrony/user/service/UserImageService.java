package com.synchrony.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.synchrony.user.dto.ImageResponse;
import com.synchrony.user.dto.ImageRequest;
import com.synchrony.user.dto.UserImageRequest;
import com.synchrony.user.entity.AppUser;
import com.synchrony.user.entity.Image;
import com.synchrony.user.repository.UserImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Repository
public class UserImageService implements IUserImageService{

    private final UserImageRepository userImageRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final UserService userService;

    @Value("${imgur.image.url}")
    private String imgurUrl;

    public UserImageService(UserImageRepository userImageRepository, UserService userService) {
        this.userImageRepository = userImageRepository;
        this.userService = userService;
    }

    @Override
    public void deleteImage(String imageId, String code) {
        String deleteHash = userImageRepository.findById(Integer.valueOf(imageId))
                .map(image -> image.getImgurDeleteHash())
                .orElseThrow();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer "+code);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        String uri = new StringBuilder(imgurUrl).append("/").append(deleteHash).toString();
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.DELETE, httpEntity,
                String.class);
        if(responseEntity.getStatusCode().is2xxSuccessful()) {
            userImageRepository.deleteById(Integer.valueOf(imageId));
        }
    }

    @Override
    public ResponseEntity<ImageResponse> fetchImage(String imageHash, String code) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer "+code);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        String uri = new StringBuilder(imgurUrl).append("/").append(imageHash).toString();
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, ImageResponse.class);
    }

    @Override
    public ResponseEntity<ImageResponse> uploadImage(ImageRequest imageRequest, String code) throws JsonProcessingException {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer "+code);
            ObjectMapper obj = new ObjectMapper();
            ObjectNode image = obj.createObjectNode();
            image.put("image", imageRequest.getImageData());
            String body = "{\n" +
                    "    \"image\": \"R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7\"\n" +
                    "}";
            HttpEntity httpEntity = new HttpEntity(body, httpHeaders);
            return restTemplate.exchange(imgurUrl, HttpMethod.POST, httpEntity, ImageResponse.class);
    }

    @Override
    public void associateImagesWithUser(String userId, UserImageRequest imageRequest) {
       AppUser appUser =  userService.fetchUserByID(Integer.parseInt(String.valueOf(userId)));
       Image image = new Image();
       image.setUser(appUser);
       image.setImgurId(imageRequest.getImgurId());
       image.setImgurDeleteHash(imageRequest.getImgurDeleteHash());
       userImageRepository.save(image);
    }
}
