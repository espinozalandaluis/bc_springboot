package com.bootcamp.service;

import com.bootcamp.entity.MembershipEntity;
import com.bootcamp.repository.IClientRepository;
import com.bootcamp.repository.IMembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipService implements  IMembershipService {

    @Autowired
    IMembershipRepository cRepository;

    @Override
    public List<MembershipEntity> GetAll() {
        return cRepository.findAll();
    }

    @Override
    public MembershipEntity Insert() {
        return null;
    }

    @Override
    public MembershipEntity Update() {
        return null;
    }

    @Override
    public Integer Delete() {
        return null;
    }

    @Override
    public MembershipEntity GetByID() {
        return null;
    }
}
