package com.bootcamp.service;

import com.bootcamp.common.exceptions.ConflictExceptions;
import com.bootcamp.common.exceptions.FunctionalException;
import com.bootcamp.entity.AfpEntity;
import com.bootcamp.entity.ClientEntity;
import com.bootcamp.entity.MembershipEntity;
import com.bootcamp.model.afp.MembershipModel;
import com.bootcamp.repository.IAfpRepository;
import com.bootcamp.repository.IClientRepository;
import com.bootcamp.repository.IMembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MembershipService implements  IMembershipService {

    @Autowired
    IMembershipRepository mRepository;
    @Autowired
    IClientRepository cRepository;
    @Autowired
    IAfpRepository aRepository;
    @Override
    public List<MembershipEntity> GetAll() {
        return mRepository.findAll();
    }

    @Override
    public ClientEntity ValidateClientByID(int idc){
        if (idc == 0){
            return null;
        }
        else {
            Optional<ClientEntity> c = cRepository.findById(idc);

            if (!c.isPresent()){//m.isEmpty
                return null;
            }
            else {
                return c.get();
            }
        }
    }

    @Override
    public AfpEntity ValidateAfpByID(int ida){
        if (ida == 0){
            return null;
        }
        else {
            Optional<AfpEntity> a = aRepository.findById(ida);

            if (!a.isPresent()){//m.isEmpty
                return null;
            }
            else {
                return a.get();
            }
        }
    }

    @Override
    public Optional<MembershipEntity> Insert(MembershipModel member) throws FunctionalException {
        if (member.getAccount() == null || member.getIdCliente() == 0 || member.getIdAfp() == 0 || member.getAmount() == 0){
            throw new FunctionalException("Los parametros ingresados no son válidos.");
        }

        ClientEntity cli = ValidateClientByID(member.getIdCliente());
        if (cli == null){
            throw new FunctionalException("El cliente NO existe o esta Inhabilitado.");
        }

        AfpEntity afp = ValidateAfpByID(member.getIdAfp());
        if (afp == null){
            throw new FunctionalException("La afp No existe o esta Inhabilitado.");
        }
        MembershipEntity membershipEntity = new MembershipEntity();
        membershipEntity.setClient(cli);
        membershipEntity.setAfp(afp);
        membershipEntity.setAmount(member.getAmount());
        membershipEntity.setAccount(member.getAccount());
        membershipEntity.setStatus(true);
        membershipEntity.setCreationDate(new Date());
        membershipEntity.setCreationUser(System.getProperty("user.name"));
        return Optional.of(mRepository.save(membershipEntity));
    }

    public Map<Integer, Optional<MembershipEntity>> Add(MembershipModel membership) throws ConflictExceptions, FunctionalException {
        Map<Integer, Optional<MembershipEntity>> rpta = new HashMap<>();
        ClientEntity clientEntity = cRepository.findById(membership.getIdCliente()).get();
        var b_afp = aRepository.findById(membership.getIdAfp());
        //
        if(clientEntity != null && !b_afp.isEmpty()) {
            var aux = mRepository.findByClientAndStatusTrue(clientEntity);
            if(aux!=null) {
                if (aux.get(0).getAfp().getId() == b_afp.get().getId()) {
                    throw new ConflictExceptions(String.format("No se pudo registrar la afiliación de %s a %s. Ya se encuentra registrado.",
                            clientEntity.getDni(),
                            b_afp.get().getDescription()));
                } else {
                    throw new ConflictExceptions(String.format("No se pudo registrar la afiliación de %s a %s. Ya se encuentra registrado a otra afp.",
                            clientEntity.getDni(),
                            b_afp.get().getDescription()));
                }
            }
            else {
                MembershipEntity membershipEntity = new MembershipEntity();
                membershipEntity.setStatus(true);
                membershipEntity.setCreationDate(new Date());
                membershipEntity.setCreationUser(System.getProperty("user.name"));
                membershipEntity.setClient(clientEntity);
                membershipEntity.setAfp(b_afp.get());
                var response =  mRepository.save(membershipEntity);
                rpta.put(-1, Optional.of(response));
                return rpta;
            }
        }
        else {
            throw new FunctionalException(String.format("No se pudo registrar la afiliación de %s",
                    clientEntity.getDni()));
        }
    }

    @Override
    public MembershipEntity GetByID(int id) {
        if (id == 0){
            return null;
        }
        else {
            Optional<MembershipEntity> m = mRepository.findById(id);

            if (!m.isPresent()){//m.isPresent
                return null;
            }
            else {
                return m.get();
            }
        }
    }
}
