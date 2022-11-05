package com.bootcamp.repository;

import com.bootcamp.entity.AfpEntity;
import com.bootcamp.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAfpRepository  extends JpaRepository<AfpEntity,Integer> {
}
