package com.bootcamp.service;

import com.bootcamp.common.exceptions.FunctionalException;
import com.bootcamp.common.exceptions.TechnicalExceptions;
import com.bootcamp.entity.AfpEntity;
import com.bootcamp.entity.ClientEntity;
import com.bootcamp.model.afp.AfpModel;

import java.util.List;
import java.util.Optional;

public interface IAfpService {
    public List<AfpEntity> GetAll();
    public Optional<AfpEntity>Insert(AfpModel afpModel) throws TechnicalExceptions, FunctionalException;
    public AfpEntity Update(AfpModel afpModel) throws FunctionalException;
    public AfpEntity Delete(Integer id) throws FunctionalException;
    public Optional<AfpEntity> FindByID(Integer id);
}
