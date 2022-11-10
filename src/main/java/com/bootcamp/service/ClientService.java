package com.bootcamp.service;

import com.bootcamp.common.exceptions.FunctionalException;
import com.bootcamp.entity.AfpEntity;
import com.bootcamp.entity.ClientEntity;
import com.bootcamp.model.afp.ClientModel;
import com.bootcamp.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService {
    @Autowired
    IClientRepository cRepository;

    @Override
    public List<ClientEntity> GetAll() {
        return cRepository.findAll();
    }

    @Override
    public Optional<ClientEntity> Insert(ClientModel clientModel) throws FunctionalException {

        var client = cRepository.findByDni(clientModel.getDni());
        ClientEntity clientEntity = new ClientEntity();
        if(client.isEmpty()){
            clientEntity.setName(clientModel.getName());
            clientEntity.setLastName(clientModel.getLastName());
            clientEntity.setDni(clientModel.getDni());
            clientEntity.setPhone(clientModel.getPhone());
            clientEntity.setEmail(clientModel.getEmail());
            return Optional.of(cRepository.save(clientEntity));
        }
        else
            throw new FunctionalException(String.format("El cliente %s ya se encuentra registrado",clientModel.getDni()));
    }

    @Override
    public ClientEntity Update(ClientModel clientModel) throws FunctionalException {
        var clientOptional = cRepository.findById(clientModel.getId());
        if(clientOptional.isEmpty()){
            throw new FunctionalException("El cliente que intenta actualizar no existe");
        }
        else{
            var clientEntity = clientOptional.get();
            clientEntity.setName(clientModel.getName());
            clientEntity.setLastName(clientModel.getLastName());
            clientEntity.setDni(clientModel.getDni());
            clientEntity.setPhone(clientModel.getPhone());
            clientEntity.setDni(clientModel.getEmail());
            clientEntity.setModificationUser(System.getProperty("user.name"));
            clientEntity.setModificationDate(new Date());
            return cRepository.save(clientEntity);
        }
    }

    @Override
    public ClientEntity DeleteById(Integer id) throws FunctionalException {
        var clientOptional = cRepository.findById(id);
        if(clientOptional.isEmpty()) {
            throw new FunctionalException("El cliente que intenta eliminar no existe");
        }
        else{
            var clientEntity = clientOptional.get();
            clientEntity.setStatus(false);
            clientEntity.setModificationUser(System.getProperty("user.name"));
            clientEntity.setModificationDate(new Date());
            cRepository.save(clientEntity);
            return clientEntity;
        }
    }

    @Override
    public Optional<ClientEntity> GetByID(Integer id) {
        return cRepository.findById(id);
    }
}
