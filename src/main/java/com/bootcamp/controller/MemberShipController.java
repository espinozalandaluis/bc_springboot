package com.bootcamp.controller;

import com.bootcamp.service.IClientService;
import com.bootcamp.service.IMembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/membership")
public class MemberShipController {
    @Autowired
    IMembershipService cService;

    @GetMapping(value = "/getall")
    public ResponseEntity GetAll() {
        var data = cService.GetAll();
        return new ResponseEntity(
                data,
                HttpStatus.OK);
    }

}
