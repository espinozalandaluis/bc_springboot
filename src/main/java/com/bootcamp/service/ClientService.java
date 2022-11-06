package com.bootcamp.service;

import com.bootcamp.entity.ClientEntity;
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
    public Optional<ClientEntity> Insert(ClientEntity clientEntity) {
        var aux = cRepository.getByDni(clientEntity.getDni());
        if (aux == null) {
            clientEntity.setStatus(true);
            clientEntity.setCreationDate(new Date());
            clientEntity.setCreationUser(System.getProperty("user.name"));
            return Optional.of(cRepository.save(clientEntity));
        }
        return Optional.empty();
    }

    @Override
    public Optional<ClientEntity> Update(ClientEntity clientEntity) {
        var aux = cRepository.findById(clientEntity.getId());
        if (aux.isPresent()) {
            clientEntity.setCreationUser(aux.get().getCreationUser());
            clientEntity.setCreationDate(aux.get().getCreationDate());
            clientEntity.setStatus(aux.get().getStatus());
            clientEntity.setModificationDate(new Date());
            clientEntity.setModificationUser(System.getProperty("user.name"));
            return Optional.of(cRepository.save(clientEntity));
        }
        return Optional.empty();
    }

    @Override
    public Boolean DeleteById(Integer id) {
        var client = cRepository.findById(id);
        if(client.isPresent()) {
            var aux = client.get();
            aux.setStatus(false);
            aux.setModificationDate(new Date());
            aux.setModificationUser(System.getProperty("user.name"));
            cRepository.save(aux);
            return true;
        }
        return false;
    }

    @Override
    public Optional<ClientEntity> GetByID(Integer id) {
        return cRepository.findById(id);
    }
}
