package com.bootcamp.controller;

import com.bootcamp.common.Constants;
import com.bootcamp.common.ResponseApi;
import com.bootcamp.common.exceptions.FunctionalException;
import com.bootcamp.common.exceptions.NotFoundException;
import com.bootcamp.common.exceptions.TechnicalExceptions;
import com.bootcamp.entity.AfpEntity;
import com.bootcamp.entity.ClientEntity;
import com.bootcamp.model.afp.ClientModel;
import com.bootcamp.service.IClientService;
import com.sun.jdi.Value;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

@RestController
@RequestMapping(value = "/client")
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    IClientService cService;
    @Operation(summary = "Función que se encarga de obtener los clientes registrados.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Correcto.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AfpEntity.class))}),
            @ApiResponse(responseCode = "201", description = "Creado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "El servidor ha encontrado una situación inesperada",
                    content = @Content),
            @ApiResponse(responseCode = "501", description = "El método solicitado no está soportado por el servidor",
                    content = @Content)
    })
    @PostMapping(value="/insert")
    public ResponseEntity<?> Insert(@RequestBody ClientModel clientModel){
        try {
            var rpta = cService.Insert(clientModel);
            logger.info(String.format("El cliente %s ya se encuentra registrado", clientModel.getDni()));
            return new ResponseEntity<Object>(ResponseApi.Response("Se registró el cliente.", Constants.SystemStatusCode.Ok, rpta), HttpStatus.CREATED);
        }
        catch (Exception exception){
            logger.error(exception.getMessage());
            if(exception instanceof TechnicalExceptions){

                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.TechnicalError,
                        Optional.empty()), HttpStatus.NOT_IMPLEMENTED);
            }
            else if(exception instanceof FunctionalException){
                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.FunctionalError,
                        Optional.empty()), HttpStatus.BAD_GATEWAY);
            }
            else{
                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.FunctionalError,
                        Optional.empty()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Operation(summary = "Función que se encarga de actualizar un Cliente.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Correcto.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientEntity.class))}),
            @ApiResponse(responseCode = "201", description = "Creado",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "El servidor no pudo encontrar el contenido solicitado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "El servidor ha encontrado una situación inesperada",
                    content = @Content),
            @ApiResponse(responseCode = "501", description = "El método solicitado no está soportado por el servidor",
                    content = @Content)

    })
    @PutMapping(value="/update")
    public ResponseEntity<?> Update(@RequestBody ClientModel clientModel){
        try{
            var data = cService.Update(clientModel);
            logger.info(String.format("El cliente %s se actualizó",data.getDni()));
            return new ResponseEntity<Object>(ResponseApi.Response(String.format("El cliente %s ha sido actualizado correctamente",data.getDni()),
                    Constants.SystemStatusCode.Ok,
                    data), HttpStatus.OK);
        }
        catch (Exception exception){
            logger.error(exception.getMessage());
            if(exception instanceof TechnicalExceptions){

                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.TechnicalError,
                        Optional.empty()), HttpStatus.NOT_IMPLEMENTED);
            }
            else if(exception instanceof FunctionalException){
                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.FunctionalError,
                        Optional.empty()), HttpStatus.BAD_GATEWAY);
            }
            else if(exception instanceof NotFoundException){
                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.FunctionalError,
                        Optional.empty()), HttpStatus.NOT_FOUND);
            }
            else{
                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.FunctionalError,
                        Optional.empty()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Operation(summary = "Función que se encarga de eliminar un Cliente.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Correcto.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AfpEntity.class))}),
            @ApiResponse(responseCode = "201", description = "Creado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error en el Servicio",
                    content = @Content),
            @ApiResponse(responseCode = "501", description = "El método solicitado no está soportado por el servidor",
                    content = @Content)
    })
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity Delete(@PathVariable(value = "id") Integer id) {
        try{
            var data = cService.DeleteById(id);
            logger.info(String.format(String.format("Se ha eliminado el cliente %s",data.getDni())));
            return new ResponseEntity<Object>(ResponseApi.Response(String.format("Se ha eliminado el cliente %s\"",data.getDni()),
                    Constants.SystemStatusCode.Ok,
                    Optional.empty()), HttpStatus.OK);
        }
        catch (Exception exception){
            logger.error(exception.getMessage());
            if(exception instanceof TechnicalExceptions){

                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.TechnicalError,
                        Optional.empty()), HttpStatus.NOT_IMPLEMENTED);
            }
            else if(exception instanceof FunctionalException){
                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.FunctionalError,
                        Optional.empty()), HttpStatus.BAD_GATEWAY);
            }
            else{
                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.FunctionalError,
                        Optional.empty()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Operation(summary = "Función que se encarga de obtener los clientes registrados.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Correcto",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AfpEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el Servicio",
                    content = @Content),
            @ApiResponse(responseCode = "501", description = "El método solicitado no está soportado por el servidor",
                    content = @Content)
    })
    @GetMapping(value = "/getall")
    public ResponseEntity GetAll() {
        try{
            var data = cService.GetAll();
            logger.info("Se obtuvieron los clientes registradas.");
            return new ResponseEntity<Object>(ResponseApi.Response("Se obtuvieron los clientes registrados..",
                    Constants.SystemStatusCode.Ok,
                    data), HttpStatus.OK);
        }
        catch (Exception exception){
            logger.error(exception.getMessage());
            if(exception instanceof TechnicalExceptions){

                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.TechnicalError,
                        Optional.empty()), HttpStatus.NOT_IMPLEMENTED);
            }
            else if(exception instanceof FunctionalException){
                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.FunctionalError,
                        Optional.empty()), HttpStatus.BAD_GATEWAY);
            }
            else{
                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.FunctionalError,
                        Optional.empty()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
