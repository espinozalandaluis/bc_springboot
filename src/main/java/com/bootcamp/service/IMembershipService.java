package com.bootcamp.service;

import com.bootcamp.entity.AfpEntity;
import com.bootcamp.entity.MembershipEntity;

import java.util.List;

public interface IMembershipService {
    public List<MembershipEntity> GetAll();
    public MembershipEntity Insert();
    public MembershipEntity Update();
    public Integer Delete();
    public MembershipEntity GetByID();
}
