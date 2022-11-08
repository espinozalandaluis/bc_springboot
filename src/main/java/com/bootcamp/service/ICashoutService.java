package com.bootcamp.service;

import com.bootcamp.entity.CashoutEntity;
import com.bootcamp.entity.ClientEntity;

import java.util.List;

public interface ICashoutService {
    public List<CashoutEntity> GetAll();
    public CashoutEntity Insert();
    //public CashoutEntity Update();
    //public Integer Delete();
    public CashoutEntity GetByID();
}
