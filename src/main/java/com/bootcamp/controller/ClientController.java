package com.bootcamp.controller;

import com.bootcamp.common.Constants;
import com.bootcamp.common.ResponseApi;
import com.bootcamp.entity.ClientEntity;
import com.bootcamp.service.IClientService;
import com.sun.jdi.Value;

import net.bytebuddy.implementation.bytecode.Throw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(value = "/client")
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    IClientService cService;

    @PostMapping(value="/insert")
    public ResponseEntity<?> Insert(@RequestBody ClientEntity clientEntity){
        try {
            var rpta = cService.Insert(clientEntity);
            if (rpta.isEmpty()) {
                logger.info("El cliente ya se encuentra registrado {}", clientEntity.getDni());
                return new ResponseEntity<Object>(ResponseApi.Response("El cliente ya se encuentra registrado.", Constants.SystemStatusCode.FunctionalError, rpta), HttpStatus.OK);
            }
            logger.info("Se registró el cliente {}", rpta.get().getId());
            return new ResponseEntity<Object>(ResponseApi.Response("Se registró el cliente.", Constants.SystemStatusCode.Ok, rpta), HttpStatus.CREATED);
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
            return new ResponseEntity<Object>(ResponseApi.Response("Error del sistema. Contactar al administrador.", Constants.SystemStatusCode.TechnicalError, Optional.empty()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="/update")
    public ResponseEntity<?> Update(@RequestBody ClientEntity clientEntity){
        try{
            var rpta = cService.Update(clientEntity);
            if(rpta.isEmpty()){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("message", "El cliente no se encuentra registrado.");
                map.put("status", Constants.SystemStatusCode.FunctionalError);
                map.put("data", Optional.empty());
                logger.info("El cliente no se encuentra registrado {}", clientEntity.getDni());
                return new ResponseEntity<Object>(map,HttpStatus.OK);
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "Se actualizó el cliente");
            map.put("status", Constants.SystemStatusCode.Ok);
            map.put("data", rpta);
            logger.info("Se actualizó el cliente {}", rpta.get().getId());
            return new ResponseEntity<Object>(map,HttpStatus.OK);
        } catch (Exception ex){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "Error del sistema. Contactar al administrador.");
            map.put("status", Constants.SystemStatusCode.TechnicalError);
            map.put("data", Optional.empty());
            return new ResponseEntity<Object>(map,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity Delete(@PathVariable(value = "id") Integer id) {
        try{
            var rpta = cService.DeleteById(id);
            if(rpta){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("message", "Se eliminó el cliente");
                map.put("status", Constants.SystemStatusCode.Ok);
                map.put("data", Optional.empty());
                logger.info("Se eliminó el cliente {}", id);
                return new ResponseEntity<Object>(map,HttpStatus.OK);
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "El cliente no se encuentra registrado.");
            map.put("status", Constants.SystemStatusCode.FunctionalError);
            map.put("data", Optional.empty());
            logger.info("El cliente no se encuentra registrado {}", id);
            return new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);
        } catch (Exception ex){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "Error del sistema. Contactar al administrador.");
            map.put("status", Constants.SystemStatusCode.TechnicalError);
            map.put("data", Optional.empty());
            return new ResponseEntity<Object>(map,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getall")
    public ResponseEntity GetAll() {
        var data = cService.GetAll();
        logger.info("Se listaron {} clientes", data.stream().count());
        return new ResponseEntity(
                data,
                HttpStatus.OK);
    }

    @GetMapping(value= "/test")
    public ResponseEntity<Object> sayBye(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", "message");
        map.put("status", "-1");
        map.put("data", "data");

        return new ResponseEntity<Object>(map,HttpStatus.OK);
    }
}
