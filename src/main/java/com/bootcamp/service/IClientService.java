package com.bootcamp.service;

import com.bootcamp.entity.ClientEntity;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    public List<ClientEntity> GetAll();
    public Optional<ClientEntity> Insert(ClientEntity clientEntity);
    public Optional<ClientEntity> Update(ClientEntity clientEntity);
    public Boolean DeleteById(Integer id);
    public Optional<ClientEntity> GetByID(Integer id);
}
