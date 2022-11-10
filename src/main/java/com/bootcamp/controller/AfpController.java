package com.bootcamp.controller;

import com.bootcamp.common.Constants;
import com.bootcamp.common.exceptions.FunctionalException;
import com.bootcamp.common.exceptions.TechnicalExceptions;
import com.bootcamp.entity.AfpEntity;
import com.bootcamp.entity.ClientEntity;
import com.bootcamp.model.afp.AfpModel;
import com.bootcamp.service.IAfpService;
import com.bootcamp.service.IClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/afp")
public class AfpController {

    @Autowired
    IAfpService cService;
    @Operation(summary = "Función que se encarga de obtener las AFPs registradas.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Lista de AFPs correcto.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AfpEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el Servicio",
                    content = @Content)
    })

    @GetMapping(value = "/getall")
    public ResponseEntity GetAll() {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            var data = cService.GetAll();
            map.put("message", "");
            map.put("status", Constants.SystemStatusCode.Ok);
            map.put("data", data);
            return new ResponseEntity<Object>(map,HttpStatus.OK);
        }
        catch (Exception exception){
            map.put("message", exception.getMessage());
            if(exception instanceof TechnicalExceptions){
                map.put("status", Constants.SystemStatusCode.TechnicalError);
                map.put("data", Optional.empty());
                return new ResponseEntity<Object>(map,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else if(exception instanceof FunctionalException){
                map.put("status", Constants.SystemStatusCode.FunctionalError);
                map.put("data", Optional.empty());
                return new ResponseEntity<Object>(map,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else{
                map.put("status", Constants.SystemStatusCode.Error);
                map.put("data", exception.getMessage());
                return new ResponseEntity<Object>(map,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping(value = "/getbyid/{id}")
    public ResponseEntity GetById(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            var data = cService.FindByID(id);
            map.put("message", "");
            map.put("status", Constants.SystemStatusCode.Ok);
            map.put("data", data);
            return new ResponseEntity<Object>(map,HttpStatus.OK);
        }
        catch (Exception exception){
            map.put("message", exception.getMessage());
            if(exception instanceof TechnicalExceptions){
                map.put("status", Constants.SystemStatusCode.TechnicalError);
                map.put("data", Optional.empty());
                return new ResponseEntity<Object>(map,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else if(exception instanceof FunctionalException){
                map.put("status", Constants.SystemStatusCode.FunctionalError);
                map.put("data", Optional.empty());
                return new ResponseEntity<Object>(map,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else{
                map.put("status", Constants.SystemStatusCode.Error);
                map.put("data", exception.getMessage());
                return new ResponseEntity<Object>(map,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    @PostMapping(value = "/insert")
    public ResponseEntity Insert(@RequestBody AfpModel afpModel) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            var data = cService.Insert(afpModel);
            map.put("message", "");
            map.put("status", Constants.SystemStatusCode.Ok);
            map.put("data", data);
            return new ResponseEntity<Object>(map,HttpStatus.OK);
        }
        catch (Exception exception){
            map.put("message", exception.getMessage());
            if(exception instanceof TechnicalExceptions){
                map.put("status", Constants.SystemStatusCode.TechnicalError);
                map.put("data", Optional.empty());
                return new ResponseEntity<Object>(map,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else if(exception instanceof FunctionalException){
                map.put("status", Constants.SystemStatusCode.FunctionalError);
                map.put("data", Optional.empty());
                return new ResponseEntity<Object>(map,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else{
                map.put("status", Constants.SystemStatusCode.Error);
                map.put("data", exception.getMessage());
                return new ResponseEntity<Object>(map,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    @PutMapping(value = "/update")
    public ResponseEntity Update(@RequestBody AfpModel afpModel) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            var data = cService.Update(afpModel);
            map.put("message", String.format("La AFP %s ha sido actualizada correctamente",data.getDescription()));
            map.put("status", Constants.SystemStatusCode.Ok);
            map.put("data", data);
            return new ResponseEntity<Object>(map,HttpStatus.OK);
        }
        catch (Exception exception){
            map.put("message", exception.getMessage());
            if(exception instanceof TechnicalExceptions){
                map.put("status", Constants.SystemStatusCode.TechnicalError);
                map.put("data", Optional.empty());
                return new ResponseEntity<Object>(map,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else if(exception instanceof FunctionalException){
                map.put("status", Constants.SystemStatusCode.FunctionalError);
                map.put("data", Optional.empty());
                return new ResponseEntity<Object>(map,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else{
                map.put("status", Constants.SystemStatusCode.Error);
                map.put("data", exception.getMessage());
                return new ResponseEntity<Object>(map,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity Delete(@PathVariable Integer id){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            var data = cService.Delete(id);
            map.put("message", String.format("Se ha eliminado la AFP %s",data.getDescription()));
            map.put("status", Constants.SystemStatusCode.Ok);
            map.put("data", "");
            return new ResponseEntity<Object>(map,HttpStatus.OK);
        }
        catch (Exception exception){
            map.put("message", exception.getMessage());
            if(exception instanceof TechnicalExceptions){
                map.put("status", Constants.SystemStatusCode.TechnicalError);
                map.put("data", Optional.empty());
                return new ResponseEntity<Object>(map,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else if(exception instanceof FunctionalException){
                map.put("status", Constants.SystemStatusCode.FunctionalError);
                map.put("data", Optional.empty());
                return new ResponseEntity<Object>(map,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else{
                map.put("status", Constants.SystemStatusCode.Error);
                map.put("data", exception.getMessage());
                return new ResponseEntity<Object>(map,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
