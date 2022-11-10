package com.bootcamp.repository;

import com.bootcamp.entity.CashoutEntity;
import com.bootcamp.entity.ClientEntity;
import com.bootcamp.entity.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICashoutRepository extends JpaRepository<CashoutEntity,Integer> {

}
