package com.example.townmarket.user.repository;

import com.example.townmarket.user.entity.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByPhoneNumber(String phoneNumber);

  Boolean existsByEmail(String email);

  Page<User> findAll(Pageable pageable);

  Optional<User> findByPhoneNumber(String phone);

  Optional<User> findByEmail(String email);


}
