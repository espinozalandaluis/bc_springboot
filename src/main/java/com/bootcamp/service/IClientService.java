package com.bootcamp.service;

import com.bootcamp.entity.ClientEntity;

import java.util.List;

public interface IClientService {
    public List<ClientEntity> GetAll();
    public ClientEntity Insert();
    public ClientEntity Update();
    public Integer Delete();
    public ClientEntity GetByID();
}
