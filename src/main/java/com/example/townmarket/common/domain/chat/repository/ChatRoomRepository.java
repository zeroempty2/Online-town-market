package com.example.townmarket.common.domain.chat.repository;

import com.example.townmarket.common.domain.chat.entity.ChatRoom;
import com.example.townmarket.common.domain.product.entity.Product;
import com.example.townmarket.common.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

  boolean existsByProductAndUser(Product product, User user);

  ChatRoom findAllByOrderByIdDesc();

}
