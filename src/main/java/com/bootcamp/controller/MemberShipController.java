package com.bootcamp.controller;

import com.bootcamp.common.Constants;
import com.bootcamp.common.ResponseApi;
import com.bootcamp.common.exceptions.FunctionalException;
import com.bootcamp.common.exceptions.TechnicalExceptions;
import com.bootcamp.entity.MembershipEntity;
import com.bootcamp.model.afp.MembershipModel;
import com.bootcamp.service.IClientService;
import com.bootcamp.service.IMembershipService;
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

import java.util.Optional;

@RestController
@RequestMapping(value = "/membership")
public class MemberShipController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    IMembershipService mService;

    @Operation(summary = "Función que se encarga de obtener las membresias registradas.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Lista de Membresias correcto.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MembershipEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el Servicio",
                    content = @Content)
    })
    @GetMapping(value = "/getall")
    public ResponseEntity GetAll() {
        try{
            var data = mService.GetAll();
            logger.info("Se obtuvieron las membresias registradas.");
            return new ResponseEntity<Object>(ResponseApi.Response("Se obtuvieron las membresias registradas.",
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

    @Operation(summary = "Función que se encarga de registrar una nueva membresia.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "La membresia se registro correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MembershipEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el Servicio",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error en el Formato",
                    content = @Content)
    })
    @PostMapping(value="/insert")
    public ResponseEntity<?> Insert(@RequestBody MembershipModel member){
        try{
            var rpta = mService.Insert(member);
            logger.info(String.format("La membresia se registro correctamente."));
            return new ResponseEntity<Object>(ResponseApi.Response("Se registró el cliente.", Constants.SystemStatusCode.Ok, rpta), HttpStatus.CREATED);
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

    @Operation(summary = "Función que se encarga de obtener una sola membresia.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Membresia mostrada correctamente.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MembershipEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el Servicio",
                    content = @Content)
    })
    @GetMapping(value = "/getmembership/{id}")
    public ResponseEntity<MembershipEntity> GetOneMembership(@PathVariable("id") int idm){
        MembershipEntity member = mService.GetByID(idm);

        if (member == null){
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.ok(member);
        }
    }

}
