package com.bootcamp.controller;

import com.bootcamp.common.Constants;
import com.bootcamp.common.ResponseApi;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    IAfpService cService;
    @Operation(summary = "Función que se encarga de obtener las AFPs registradas.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Lista de AFPs correcta.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AfpEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el Servicio",
                    content = @Content)
    })
    @GetMapping(value = "/getall")
    public ResponseEntity GetAll() {
        try{
            var data = cService.GetAll();
            logger.info("Se obtuvieron las AFPs registradas.");
            return new ResponseEntity<Object>(ResponseApi.Response("Se obtuvieron las AFPs registradas.",
                    Constants.SystemStatusCode.Ok,
                    data), HttpStatus.OK);
        }
        catch (Exception exception){
            logger.error(exception.getMessage());
            if(exception instanceof TechnicalExceptions){

                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.TechnicalError,
                        Optional.empty()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else if(exception instanceof FunctionalException){
                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.FunctionalError,
                        Optional.empty()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else{
                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.FunctionalError,
                        Optional.empty()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Operation(summary = "Función que se encarga de obtener una AFP por ID.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "AFP correcto.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AfpEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el Servicio",
                    content = @Content)
    })
    @GetMapping(value = "/getbyid/{id}")
    public ResponseEntity GetById(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            var data = cService.FindByID(id);
            logger.info(String.format("Se obtuvo la AFP %s", data.get().getDescription()));
            return new ResponseEntity<Object>(ResponseApi.Response(String.format("Se obtuvo la AFP %s", data.get().getDescription()),
                    Constants.SystemStatusCode.Ok,
                    data), HttpStatus.OK);
        }
        catch (Exception exception){
            logger.error(exception.getMessage());
            if(exception instanceof TechnicalExceptions){

                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.TechnicalError,
                        Optional.empty()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else if(exception instanceof FunctionalException){
                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.FunctionalError,
                        Optional.empty()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else{
                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.FunctionalError,
                        Optional.empty()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    @Operation(summary = "Función que se encarga de insertar una nueva AFP.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Correcto.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AfpEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el Servicio",
                    content = @Content)
    })
    @PostMapping(value = "/insert")
    public ResponseEntity Insert(@RequestBody AfpModel afpModel) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            var data = cService.Insert(afpModel);
            logger.info("Se insertó la AFP correctamente.");
            return new ResponseEntity<Object>(ResponseApi.Response("Se insertó la AFP correctamente.",
                    Constants.SystemStatusCode.Ok,
                    data), HttpStatus.OK);
        }
        catch (Exception exception){
            logger.error(exception.getMessage());
            if(exception instanceof TechnicalExceptions){

                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.TechnicalError,
                        Optional.empty()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else if(exception instanceof FunctionalException){
                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.FunctionalError,
                        Optional.empty()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else{
                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.FunctionalError,
                        Optional.empty()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    @Operation(summary = "Función que se encarga de actualizar una AFP.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Correcto.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AfpEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el Servicio",
                    content = @Content)
    })
    @PutMapping(value = "/update")
    public ResponseEntity Update(@RequestBody AfpModel afpModel) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            var data = cService.Update(afpModel);
            logger.info(String.format("La AFP %s ha sido actualizada correctamente",data.getDescription()));
            return new ResponseEntity<Object>(ResponseApi.Response(String.format("La AFP %s ha sido actualizada correctamente",data.getDescription()),
                    Constants.SystemStatusCode.Ok,
                    data), HttpStatus.OK);
        }
        catch (Exception exception){
            logger.error(exception.getMessage());
            if(exception instanceof TechnicalExceptions){

                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.TechnicalError,
                        Optional.empty()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else if(exception instanceof FunctionalException){
                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.FunctionalError,
                        Optional.empty()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else{
                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.FunctionalError,
                        Optional.empty()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    @Operation(summary = "Función que se encarga de eliminar una AFP.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Correcto.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AfpEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el Servicio",
                    content = @Content)
    })
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity Delete(@PathVariable Integer id){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            var data = cService.Delete(id);
            logger.info(String.format(String.format("Se ha eliminado la AFP %s",data.getDescription())));
            return new ResponseEntity<Object>(ResponseApi.Response(String.format("Se ha eliminado la AFP %s",data.getDescription()),
                    Constants.SystemStatusCode.Ok,
                    Optional.empty()), HttpStatus.OK);
        }
        catch (Exception exception){
            logger.error(exception.getMessage());
            if(exception instanceof TechnicalExceptions){

                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.TechnicalError,
                        Optional.empty()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else if(exception instanceof FunctionalException){
                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.FunctionalError,
                        Optional.empty()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else{
                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.FunctionalError,
                        Optional.empty()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
