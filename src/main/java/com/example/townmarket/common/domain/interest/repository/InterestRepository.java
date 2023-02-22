package com.example.townmarket.common.domain.interest.repository;

import com.example.townmarket.common.domain.interest.entity.Interest;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Interest.class, idClass = Long.class)
public interface InterestRepository extends JpaRepository<Interest, Long>, InterestRepositoryQuery {


  Optional<Interest> findByUserIdAndProductId(Long userId, Long productId);
}
