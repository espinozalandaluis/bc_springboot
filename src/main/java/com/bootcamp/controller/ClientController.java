package com.bootcamp.controller;

import com.bootcamp.entity.ClientEntity;
import com.bootcamp.service.IClientService;
import com.sun.jdi.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/client")
public class ClientController {
    @Autowired
    IClientService cService;

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
