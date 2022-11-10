package com.bootcamp.controller;

import com.bootcamp.common.Constants;
import com.bootcamp.common.ResponseApi;
import com.bootcamp.common.exceptions.FunctionalException;
import com.bootcamp.common.exceptions.TechnicalExceptions;
import com.bootcamp.entity.CashoutEntity;
import com.bootcamp.model.afp.CashoutModel;
import com.bootcamp.service.IAfpService;
import com.bootcamp.service.ICashoutService;
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
@RequestMapping(value = "/cashout")
public class CashoutController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    ICashoutService cService;

    @Operation(summary = "Función que se encarga de devolver todos las solicitudes de retiro.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Lista de Solicitude de Retiro de forma correcta",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CashoutEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el Servicio",
                    content = @Content)
    })
    @GetMapping(value = "/getall")
    public ResponseEntity GetAll() {
        try{
            var data = cService.GetAll();
            logger.info("Se obtuvieron las solicitudes de retiro.");
            return new ResponseEntity<Object>(ResponseApi.Response("Se obtuvieron las solicitudes de retiro.",
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

    @Operation(summary = "Función que se encarga de registrar una solicitud de retiro de afp.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Solicitud de Retiro de afp realizado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CashoutEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el Servicio",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error en el Formato",
                    content = @Content)
    })
    @PostMapping(value="/insert")
    public ResponseEntity<?> Insert(@RequestBody CashoutModel cash){
        try{
            var rpta = cService.Insert(cash);
            logger.info("La solicitud de retiro se realizo correctamente.");
            return new ResponseEntity<Object>(ResponseApi.Response("La solicitud de retiro se realizo correctamente.", Constants.SystemStatusCode.Ok, rpta), HttpStatus.CREATED);
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
