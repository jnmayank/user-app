package com.synchrony.user.repository;

import com.synchrony.user.entity.AppUser;
import com.synchrony.user.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserImageRepository extends JpaRepository<Image, Integer> {

}
