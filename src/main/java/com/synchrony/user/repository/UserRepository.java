package com.synchrony.user.repository;

import com.synchrony.user.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByUserName(String username);

}
