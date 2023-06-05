package com.synchrony.user.service;

import com.synchrony.user.repository.UserImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class UserImageService implements IUserImageService{

    private final UserImageRepository userImageRepository;

    @Autowired
    private RestTemplate restTemplate;

    public UserImageService(UserImageRepository userImageRepository) {
        this.userImageRepository = userImageRepository;
    }

    @Override
    public void deleteImage(String imageId) {
        String deleteHash = userImageRepository.findById(Integer.valueOf(imageId))
                .map(image -> image.getImgurDeleteHash())
                .orElseThrow();

        MultiValueMap
        HttpEntity httpEntity = new HttpEntity();
        restTemplate.exchange("url", HttpMethod.DELETE, )
    }
}
