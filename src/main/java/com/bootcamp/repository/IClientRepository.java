package com.bootcamp.repository;

import com.bootcamp.entity.AfpEntity;
import com.bootcamp.entity.ClientEntity;
import com.bootcamp.entity.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClientRepository extends JpaRepository<ClientEntity,Integer> {
    List<ClientEntity> findByDni(String dni);
    //List<MembershipEntity> findByClientAndStatusTrue(ClientEntity clientEntity);
}
