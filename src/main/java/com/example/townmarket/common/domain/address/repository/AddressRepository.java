package com.example.townmarket.common.domain.address.repository;

import com.example.townmarket.common.domain.address.entity.Address;
import com.example.townmarket.common.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Address.class, idClass = Long.class)
public interface AddressRepository extends JpaRepository<Address, Long>,AddressRepositoryQuery {

  Address findByUser(User user);

}
