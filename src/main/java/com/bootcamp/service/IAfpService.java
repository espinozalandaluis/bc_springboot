package com.bootcamp.service;

import com.bootcamp.entity.AfpEntity;
import com.bootcamp.entity.ClientEntity;

import java.util.List;

public interface IAfpService {
    public List<AfpEntity> GetAll();
    public AfpEntity Insert();
    public AfpEntity Update();
    public Integer Delete();
    public AfpEntity GetByID();
}
