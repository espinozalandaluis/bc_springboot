package com.bootcamp.service;

import com.bootcamp.common.exceptions.FunctionalException;
import com.bootcamp.entity.CashoutEntity;
import com.bootcamp.entity.ClientEntity;
import com.bootcamp.entity.MembershipEntity;
import com.bootcamp.model.afp.CashoutModel;

import java.util.List;
import java.util.Optional;

public interface ICashoutService {
    public List<CashoutEntity> GetAll();
    public Optional<CashoutEntity> Insert(CashoutModel cash) throws FunctionalException;
    //public CashoutEntity Update();
    //public Integer Delete();
    public Optional<CashoutEntity> GetByID(int id);

    public MembershipEntity GetByIDForCashout(int id);
    public Optional<MembershipEntity> UpdateAmount(MembershipEntity member);
}
