package com.bootcamp.controller;

import com.bootcamp.common.Constants;
import com.bootcamp.entity.ClientEntity;
import com.bootcamp.service.IClientService;
import com.sun.jdi.Value;
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
    @Autowired
    IClientService cService;

    @PostMapping(value="/insert")
    public ResponseEntity<?> Insert(@RequestBody ClientEntity clientEntity){
        var rpta = cService.Insert(clientEntity);
        if(rpta.isEmpty()){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "El cliente ya se encuentra registrado.");
            map.put("status", Constants.SystemStatusCode.FunctionalError);
            map.put("data", rpta);
            return new ResponseEntity<Object>(map,HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(rpta);

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
                return new ResponseEntity<Object>(map,HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.FOUND).body(rpta);
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
