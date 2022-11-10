package com.bootcamp.controller;

import com.bootcamp.common.Constants;
import com.bootcamp.common.ResponseApi;
import com.bootcamp.common.exceptions.ConflictExceptions;
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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
                    content = @Content),
            @ApiResponse(responseCode = "501", description = "El método solicitado no está soportado por el servidor",
                    content = @Content),
            @ApiResponse(responseCode = "502", description = "Respuesta invalida",
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

    /*
    @Operation(summary = "Función que se encarga de registrar una nueva membresia.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "La membresia se registro correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MembershipEntity.class))}),
            @ApiResponse(responseCode = "201", description = "Creado",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error en el Formato",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "El servidor no pudo encontrar el contenido solicitado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error en el Servicio",
                    content = @Content),
            @ApiResponse(responseCode = "501", description = "El método solicitado no está soportado por el servidor",
                    content = @Content),
            @ApiResponse(responseCode = "502", description = "Respuesta invalida",
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
    */
    @Operation(summary = "Función que se encarga de vincular un cliente a la afp.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Se registró la afiliación.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MembershipEntity.class))}),
            @ApiResponse(responseCode = "201", description = "Creado",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Datos de cliente o afp no concuerdan.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MembershipEntity.class))}),
            @ApiResponse(responseCode = "409", description = "El cliente ya se encuentra registrado a esta afp.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MembershipEntity.class))}),
            @ApiResponse(responseCode = "302", description = "El cliente ya se encuentra registrado a otra afp.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MembershipEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Error en el Servicio",
                    content = @Content),
            @ApiResponse(responseCode = "501", description = "El método solicitado no está soportado por el servidor",
                    content = @Content),
            @ApiResponse(responseCode = "502", description = "Respuesta invalida",
                    content = @Content)
    })

    @PostMapping(value="/insert")
    public ResponseEntity<?> Insert(@RequestBody MembershipModel membershipEntity){
        try {
            var rpta = mService.Add(membershipEntity);
            Iterator<Integer> key = rpta.keySet().iterator();
            Map<String, Object> map = new HashMap<String, Object>();
            switch (key.next()){
                case -1:
                    logger.info("Se registró la afiliación");
                    return new ResponseEntity<Object>(ResponseApi.Response("Se registró la afiliación.", Constants.SystemStatusCode.FunctionalError, rpta), HttpStatus.CREATED);
            }
            return new ResponseEntity<Object>(map, HttpStatus.OK);
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
            else if(exception instanceof ConflictExceptions){
                return new ResponseEntity<Object>(ResponseApi.Response(exception.getMessage(),
                        Constants.SystemStatusCode.FunctionalError,
                        Optional.empty()), HttpStatus.CONFLICT);
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
                    content = @Content),
            @ApiResponse(responseCode = "501", description = "El método solicitado no está soportado por el servidor",
                    content = @Content),
            @ApiResponse(responseCode = "502", description = "Respuesta invalida",
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
