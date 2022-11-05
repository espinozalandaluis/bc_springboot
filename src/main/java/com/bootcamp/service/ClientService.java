package com.bootcamp.service;

import com.bootcamp.entity.ClientEntity;
import com.bootcamp.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements IClientService {
    @Autowired
    IClientRepository cRepository;

    @Override
    public List<ClientEntity> GetAll() {
        return cRepository.findAll();
    }

    @Override
    public ClientEntity Insert() {
        return null;
    }

    @Override
    public ClientEntity Update() {
        return null;
    }

    @Override
    public Integer Delete() {
        return null;
    }

    @Override
    public ClientEntity GetByID() {
        return null;
    }
}
