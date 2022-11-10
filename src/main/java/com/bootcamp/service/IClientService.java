package com.bootcamp.service;

import com.bootcamp.common.exceptions.FunctionalException;
import com.bootcamp.common.exceptions.NotFoundException;
import com.bootcamp.entity.ClientEntity;
import com.bootcamp.model.afp.ClientModel;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    public List<ClientEntity> GetAll();
    public Optional<ClientEntity> Insert(ClientModel clientModel) throws FunctionalException;
    public ClientEntity Update(ClientModel clientModel) throws FunctionalException, NotFoundException;
    public ClientEntity DeleteById(Integer id) throws FunctionalException;
    public Optional<ClientEntity> GetByID(Integer id);
}
