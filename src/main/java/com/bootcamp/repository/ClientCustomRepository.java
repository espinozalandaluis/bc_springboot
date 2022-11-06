package com.bootcamp.repository;

import com.bootcamp.entity.ClientEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class ClientCustomRepository implements IClientCustomRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ClientEntity getByDni(String dni) {
        try {
            Query query = entityManager.createNativeQuery("SELECT * FROM db_a26cd3_microse.client WHERE vc_dni = ?", ClientEntity.class);
            query.setParameter(1, dni);

            return (ClientEntity) query.getSingleResult();
        }
        catch (NoResultException ex){
            return null;
        }

    }
}
