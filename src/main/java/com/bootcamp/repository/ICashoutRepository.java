package com.bootcamp.repository;

import com.bootcamp.entity.CashoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICashoutRepository extends JpaRepository<CashoutEntity,Integer> {
}
