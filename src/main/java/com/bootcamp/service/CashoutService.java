package com.bootcamp.service;

import com.bootcamp.common.exceptions.FunctionalException;
import com.bootcamp.entity.CashoutEntity;
import com.bootcamp.entity.MembershipEntity;
import com.bootcamp.model.afp.CashoutModel;
import com.bootcamp.repository.ICashoutRepository;
import com.bootcamp.repository.IClientRepository;
import com.bootcamp.repository.IMembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CashoutService implements ICashoutService{

    @Autowired
    ICashoutRepository cRepository;
    @Autowired
    IMembershipRepository mRepository;
    @Override
    public List<CashoutEntity> GetAll() {
        return cRepository.findAll();
    }

    @Override
    public MembershipEntity GetByIDForCashout(int id){
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

    @Override
    public Optional<MembershipEntity> UpdateAmount(MembershipEntity member) {
        return Optional.of(mRepository.save(member));
    }
    @Override
    public Optional<CashoutEntity> Insert(CashoutModel cash) throws FunctionalException {
        MembershipEntity rpta = GetByIDForCashout(cash.getId());

        if (rpta != null){
            int idmember = rpta.getId(); //OBTENER ID DEL MEMBERSHIP
            double dmontototal = rpta.getAmount(); //OBTENER MONTO DEL MEMBERSHIP
            double dmontominimo = dmontototal * 0.5; //MONTO DEL 50% DEL TOTAL
            boolean bActivo = rpta.getStatus();//SI ESTA ACTIVO O NO LA MEMBRESIA

            if (bActivo){
                if (cash.getAmount() > dmontominimo){
                    if (cash.getAmount() < dmontototal){
                        CashoutEntity cashEntity = new CashoutEntity();
                        cashEntity.setCreationDate(new Date());
                        cashEntity.setCashoutDate(new Date());
                        cashEntity.setModificationDate(new Date());
                        cashEntity.setCreationUser(System.getProperty("user.name"));

                        rpta.setAmount(dmontototal - dmontominimo);
                        rpta.setModificationDate(new Date());
                        rpta.setModificationUser(System.getProperty("user.name"));

                        UpdateAmount(rpta);//ACTUALIZACION DEL MONTO

                        return Optional.of(cRepository.save(cashEntity));
                    }
                    else {//MENSAJE: No se puede registrar la solicitud. Monto mayor que el permitido
                        throw new FunctionalException("No se puede registrar la solicitud. Monto mayor que el permitido.");
                    }
                }
                else {//MENSAJE : Monto mínimo no cubierto por favor revise el monto mínimo a retirar
                    throw new FunctionalException("Monto mínimo no cubierto por favor revise el monto mínimo a retirar.");
                }
            }
        }

        return null;//Optional.empty();
    }

    @Override
    public Optional<CashoutEntity> GetByID(int id) {
        return cRepository.findById(id);
    }
}
