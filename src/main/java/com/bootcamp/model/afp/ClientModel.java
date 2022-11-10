package com.bootcamp.model.afp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientModel {
    private Integer id;
    @Schema(description = "campo que define el nombre del cliente.",
            type = "string",
            required = true,
            example = "abc...xyz")
    private String name;

    @Schema(description = "campo que define el apellido del cliente.",
            type = "string",
            required = true,
            example = "abc...xyz")
    private String lastName;

    @Schema(description = "campo que define el DNI del cliente.",
            type = "string",
            required = true,
            example = "12345678")
    private String dni;

    @Schema(description = "campo que define el telefono del cliente.",
            type = "string",
            required = true,
            example = "55555555")
    private String phone;

    @Schema(description = "campo que define el correo del cliente.",
            type = "string",
            required = true,
            example = "abc@xyz.com")
    private String email;
}
