package com.bootcamp.service;

import com.bootcamp.common.exceptions.FunctionalException;
import com.bootcamp.common.exceptions.TechnicalExceptions;
import com.bootcamp.entity.AfpEntity;
import com.bootcamp.entity.ClientEntity;
import com.bootcamp.model.afp.AfpModel;
import com.bootcamp.repository.IAfpRepository;
import com.bootcamp.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AfpService implements  IAfpService {
    @Autowired
    IAfpRepository cRepository;

    @Override
    public List<AfpEntity> GetAll() {
        return cRepository.findAll();
    }

    @Override
    public Optional<AfpEntity>Insert(AfpModel afpModel) throws TechnicalExceptions, FunctionalException {

        var afp = cRepository.findByDescription(afpModel.getDescription().toUpperCase().trim());
        AfpEntity afpEntity = new AfpEntity();
        if(afp.isEmpty()) {
            afpEntity.setDescription(afpModel.getDescription().toUpperCase().trim());
            return Optional.of(cRepository.save(afpEntity));
        }
        else
            throw new FunctionalException("La AFP que intenta ingresar ya existe");
    }

    @Override
    public AfpEntity Update(AfpModel afpModel) throws FunctionalException {

        var afpOptional = cRepository.findById(afpModel.getId());
        if(afpOptional.isEmpty())
            throw new FunctionalException("La AFP que intenta actualizar no existe");
        else {
            var afpEntity = afpOptional.get();
            afpEntity.setDescription(afpModel.getDescription().toUpperCase().trim());
            afpEntity.setModificationUser("ADMIN");
            afpEntity.setModificationDate(new Date());
            return cRepository.save(afpEntity);
        }
    }

    @Override
    public AfpEntity Delete(Integer id) throws FunctionalException {
        var afpOptional = cRepository.findById(id);
        if(afpOptional.isEmpty()) {
            throw new FunctionalException("La AFP que intenta eliminar no existe");
        }
        else{
            var afpEntity = afpOptional.get();
            afpEntity.setStatus(false);
            cRepository.save(afpEntity);
            return afpEntity;
        }
    }

    @Override
    public Optional<AfpEntity> FindByID(Integer id) {
        return  cRepository.findById(id);
    }
}
