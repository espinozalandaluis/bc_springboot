package com.bootcamp.repository;

import com.bootcamp.entity.AfpEntity;
import com.bootcamp.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClientRepository extends JpaRepository<ClientEntity,Integer>, IClientCustomRepository {
    List<ClientEntity> findByDni(String dni);
}
