package com.bootcamp.repository;

import com.bootcamp.entity.ClientEntity;

public interface IClientCustomRepository {
    public ClientEntity getByDni(String dni);
}
