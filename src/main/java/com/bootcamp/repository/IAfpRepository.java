package com.bootcamp.repository;

import com.bootcamp.entity.AfpEntity;
import com.bootcamp.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAfpRepository  extends JpaRepository<AfpEntity,Integer> {
    List<AfpEntity> findByDescription(String description);

}
