package com.bootcamp.service;

import com.bootcamp.entity.CashoutEntity;
import com.bootcamp.repository.ICashoutRepository;
import com.bootcamp.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashoutService implements ICashoutService{

    @Autowired
    ICashoutRepository cRepository;

    @Override
    public List<CashoutEntity> GetAll() {
        return cRepository.findAll();
    }

    @Override
    public CashoutEntity Insert() {
        return null;
    }

    //@Override
    //public CashoutEntity Update() {
      //  return null;
    //}

    //@Override
    //public Integer Delete() {
      //  return null;
    //}

    @Override
    public CashoutEntity GetByID() {
        return null;
    }
}
