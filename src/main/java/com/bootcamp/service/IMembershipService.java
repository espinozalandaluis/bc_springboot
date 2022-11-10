package com.bootcamp.service;

import com.bootcamp.common.exceptions.FunctionalException;
import com.bootcamp.entity.AfpEntity;
import com.bootcamp.entity.ClientEntity;
import com.bootcamp.entity.MembershipEntity;
import com.bootcamp.model.afp.MembershipModel;

import java.util.List;
import java.util.Optional;

public interface IMembershipService {
    public List<MembershipEntity> GetAll();
    public Optional<MembershipEntity> Insert(MembershipModel member) throws FunctionalException;
    //public Optional<MembershipEntity> Update(MembershipEntity member);
    //public Integer Delete(int id);
    public MembershipEntity GetByID(int id);

    public ClientEntity ValidateClientByID(int idc);
    public AfpEntity ValidateAfpByID(int ida);
}
