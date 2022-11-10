package com.bootcamp.repository;

import com.bootcamp.entity.ClientEntity;
import com.bootcamp.entity.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMembershipRepository extends JpaRepository<MembershipEntity,Integer> {
    List<MembershipEntity> findByClientAndStatusTrue(ClientEntity clientEntity);
}
