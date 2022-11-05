package com.bootcamp.service;

import com.bootcamp.entity.AfpEntity;
import com.bootcamp.repository.IAfpRepository;
import com.bootcamp.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AfpService implements  IAfpService {
    @Autowired
    IAfpRepository cRepository;

    @Override
    public List<AfpEntity> GetAll() {
        return cRepository.findAll();
    }

    @Override
    public AfpEntity Insert() {
        return null;
    }

    @Override
    public AfpEntity Update() {
        return null;
    }

    @Override
    public Integer Delete() {
        return null;
    }

    @Override
    public AfpEntity GetByID() {
        return null;
    }
}
